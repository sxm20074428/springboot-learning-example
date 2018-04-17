package com.sxm.springboot.common.exception.handler;

import com.sxm.springboot.common.dto.ResultResponse;
import com.sxm.springboot.common.exception.BusinessException;
import com.sxm.springboot.common.exception.BusinessJSONException;
import com.sxm.springboot.validation.dto.ValidationErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;

/**
 * 统一错误码异常处理
 * <p>
 * '@ControllerAdvice' annotation that we can use with any class to define our global exception handler.
 * The handler methods in Global Controller Advice is same as Controller based exception handler methods
 * and used when controller class is not able to handle the exception.
 * ExceptionHandler本身只能捕获当前类的异常信息，结合@ControllerAdvice可以捕获全部controller异常。
 * Spring MVC 3.2 introduced a new @ControllerAdvice annotation
 * which we can use to implement an exception handler component that processes the exceptions thrown by our controllers
 * *
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/7/30 18:10
 * @since 0.1
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(HttpServletRequest request, Exception exception) {
        LOGGER.error("handleException", exception);
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
        LOGGER.error("handleBusinessJSONException", exception);
        return ResultResponse.createErrorResponse(exception);
    }


    @Autowired
    /**
     * The message source is used to fetch localized error message for validation errors.
     */
    private MessageSource messageSource;

    //    @ExceptionHandler(BindException.class)
    //    @ResponseStatus(HttpStatus.BAD_REQUEST)
    //    public ResultResponse processValidationError(BindException e) {
    //        List<FieldError> fieldErrors = e.getFieldErrors();
    //        Locale locale = LocaleContextHolder.getLocale();
    //        StringBuilder errorMessage = new StringBuilder();
    //        fieldErrors.forEach(fieldError -> {
    //            errorMessage.append(fieldError.getField())
    //                    .append(":")
    //                    .append(messageSource.getMessage(fieldError, locale))
    //                    .append(",");
    //        });
    //
    //        return ResultResponse.createErrorResponse(errorMessage.toString());
    //    }


    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorDTO processValidationError(BindException e) {

        LOGGER.debug("Handling form validation error");

        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ValidationErrorDTO processValidationError(MethodArgumentNotValidException e) {

        LOGGER.debug("Handling form validation error");

        BindingResult bindingResult = e.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }


    private ValidationErrorDTO processFieldErrors(List<FieldError> fieldErrors) {

        ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO();
        for (FieldError fieldError : fieldErrors) {
            String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
            LOGGER.debug("Adding error message: {} to field: {}", localizedErrorMessage, fieldError.getField());
            validationErrorDTO.addFieldError(fieldError.getField(), localizedErrorMessage);
        }

        return validationErrorDTO;
    }

    /**
     * resolve a localized error message by using the MessageSource object, current locale and the error code of the processed field error
     *
     * @author 苏晓蒙
     * @time 2018/4/9 0009 上午 9:48
     * @version 0.1
     * @since 0.1
     */
    private String resolveLocalizedErrorMessage(FieldError fieldError) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        String localizedErrorMessage = messageSource.getMessage(fieldError, currentLocale);
        return localizedErrorMessage;
    }
}
