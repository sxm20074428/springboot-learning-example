package com.sxm.springboot.validator.dto;

/**
 * DTO which contains the information of a single validation error
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/4/9 0009 上午 9:37
 * @since 0.1
 */
public class FieldErrorDTO {

    private String field;

    private String message;

    public FieldErrorDTO(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
