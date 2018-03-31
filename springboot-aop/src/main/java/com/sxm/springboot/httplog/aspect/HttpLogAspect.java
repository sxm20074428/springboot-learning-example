package com.sxm.springboot.httplog.aspect;

import com.sxm.springboot.common.BaseResponse;
import com.sxm.springboot.httplog.annotation.HttpLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

@Component
@Aspect
public class HttpLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpLogAspect.class);

    @Pointcut("@annotation(com.sxm.springboot.httplog.annotation.HttpLog)")
    public void logAnnotation() {
    }

    private static ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<String> traceIdThreadLocal = new ThreadLocal<>();

    private Optional<HttpLog> getLogAnnotation(JoinPoint joinPoint) {
        if (joinPoint instanceof MethodInvocationProceedingJoinPoint) {
            Signature signature = joinPoint.getSignature();
            if (signature instanceof MethodSignature) {
                MethodSignature methodSignature = (MethodSignature) signature;
                Method method = methodSignature.getMethod();
                if (method.isAnnotationPresent(HttpLog.class)) {
                    return Optional.of(method.getAnnotation(HttpLog.class));
                }
            }
        }
        return Optional.empty();
    }

    /**
     * 获取HttpServletRequest
     */
    private HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    private String getRequestPath(HttpServletRequest request) {
        return (null != request.getServletPath() && request.getServletPath().length() > 0) ? request.getServletPath() : request.getPathInfo();
    }

    private long costTime() {
        return System.currentTimeMillis() - startTimeThreadLocal.get();
    }

    @Before("logAnnotation()")
    public void requestLog(JoinPoint joinPoint) {
        try {
            Optional<HttpLog> httpLog = getLogAnnotation(joinPoint);
            httpLog.ifPresent(anno -> {
                HttpServletRequest request = getRequest();
                traceIdThreadLocal.set(UUID.randomUUID().toString());
                startTimeThreadLocal.set(System.currentTimeMillis());
                List<String> excludes = Arrays.asList(anno.exclude());
                List<Object> params = new ArrayList<>();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("REQUEST_LOG. traceId:{}. ").append("requestUrl: ").append(getRequestPath(request)).append(" -PARAMS- ");
                params.add(traceIdThreadLocal.get());
                request.getParameterMap().forEach((k, v) -> {
                    if (!excludes.contains(k)) {
                        stringBuilder.append(k).append(": {}, ");
                        params.add(v);
                    }
                });
                if (anno.headerParams().length > 0) {
                    stringBuilder.append(" -HEADER_PARAMS- ");
                    Arrays.asList(anno.headerParams()).forEach(param -> {
                        stringBuilder.append(param).append(": {}, ");
                        params.add(request.getHeader(param));
                    });
                }
                LOGGER.info(stringBuilder.toString(), params.toArray());
            });
        } catch (Exception ignore) {
            LOGGER.error("print request LOGGER fail!", ignore);
        }
    }

    @AfterReturning(returning = "baseResponse", pointcut = "logAnnotation()")
    public void response(JoinPoint joinPoint, BaseResponse baseResponse) {
        try {
            Optional<HttpLog> httpLog = getLogAnnotation(joinPoint);
            httpLog.ifPresent(anno -> {
                if (!anno.ignoreResponse()) {
                    LOGGER.info("RESPONSE_LOG. traceId:{}, result:{}, cost:{}", traceIdThreadLocal.get(), baseResponse, costTime());
                }
            });
        } catch (Exception ignore) {
            LOGGER.error("print response LOGGER fail!", ignore);
        } finally {
            traceIdThreadLocal.remove();
            startTimeThreadLocal.remove();
        }
    }

    @AfterThrowing(throwing = "e", pointcut = "logAnnotation()")
    public void throwing(JoinPoint joinPoint, Exception e) {
        try {
            Optional<HttpLog> httpLog = getLogAnnotation(joinPoint);
            httpLog.ifPresent(anno -> {
                if (!anno.ignoreResponse()) {
                    LOGGER.info("ERROR_LOG. traceId:{}, cost:{}", traceIdThreadLocal.get(), costTime(), e);
                }
            });
        } catch (Exception ignore) {
            LOGGER.error("print error LOGGER fail!", ignore);
        } finally {
            traceIdThreadLocal.remove();
            startTimeThreadLocal.remove();
        }
    }

}