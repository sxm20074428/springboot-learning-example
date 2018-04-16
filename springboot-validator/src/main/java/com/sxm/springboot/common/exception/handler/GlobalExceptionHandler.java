package com.sxm.springboot.common.exception.handler;

import com.sxm.springboot.common.dto.ResultResponse;
import com.sxm.springboot.common.exception.BusinessException;
import com.sxm.springboot.common.exception.BusinessJSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一错误码异常处理
 * <p>
 * '@ControllerAdvice' annotation that we can use with any class to define our global exception handler.
 * The handler methods in Global Controller Advice is same as Controller based exception handler methods
 * and used when controller class is not able to handle the exception.
 * ExceptionHandler本身只能捕获当前类的异常信息，结合@ControllerAdvice可以捕获全部controller异常。
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/7/30 18:10
 * @since 0.1
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(HttpServletRequest request, Exception exception) {
        logger.error("handleException", exception);
        request.setAttribute("exception", exception);
        // 根据不同错误转向不同页面
        if (exception instanceof BusinessException) {
            return "error/error-business";
        } else {
            return "error/error";
        }
    }

    @ExceptionHandler(BusinessJSONException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResultResponse handleBusinessJSONException(BusinessJSONException exception) {
        logger.error("handleBusinessJSONException", exception);
        return ResultResponse.createErrorResponse(exception);
    }

}
