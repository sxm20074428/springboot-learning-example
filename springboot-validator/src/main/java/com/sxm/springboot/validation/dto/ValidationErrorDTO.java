package com.sxm.springboot.validation.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * DTO which wraps those validation errors together
 * it contains a list of FieldErrorDTO objects and a method which is used to add new field errors to the list.
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/4/9 0009 上午 9:39
 * @since 0.1
 */
public class ValidationErrorDTO {

    private List<FieldErrorDTO> fieldErrors = new ArrayList<>();

    public ValidationErrorDTO() {
    }

    public void addFieldError(String field, String message) {

        FieldErrorDTO fieldErrorDTO = new FieldErrorDTO(field, message);
        fieldErrors.add(fieldErrorDTO);
    }

    public List<FieldErrorDTO> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldErrorDTO> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
