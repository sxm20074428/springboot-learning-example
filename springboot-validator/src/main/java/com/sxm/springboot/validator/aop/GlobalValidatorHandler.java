package com.sxm.springboot.validator.aop;

import com.sxm.springboot.validator.dto.ValidationErrorDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Locale;

/**
 * Spring MVC 3.2 introduced a new @ControllerAdvice annotation
 * which we can use to implement an exception handler component that processes the exceptions thrown by our controllers
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/4/9 0009 上午 10:29
 * @since 0.1
 */
@RestControllerAdvice
public class GlobalValidatorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalValidatorHandler.class);


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


    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorDTO processValidationError(BindException e) {

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
