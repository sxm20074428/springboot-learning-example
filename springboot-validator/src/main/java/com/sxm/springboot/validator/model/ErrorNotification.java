
package com.sxm.springboot.validator.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ErrorNotification {

    private List<String> errorList = new ArrayList<>();

    public void addAll(ErrorNotification errorNotification) {
        this.errorList.addAll(errorNotification.errorList);
    }

    public boolean hasError() {
        return !errorList.isEmpty();
    }

    public String getAllErrors() {
        return errorList.stream()
                .collect(Collectors.joining(", "));
    }

    /**
     * copies all errors from another ErrorNotification object
     *
     * @param
     * @return
     * @author 苏晓蒙
     * @time 2018/4/10 0010 下午 14:33
     * @version 0.1
     * @since 0.1
     */
    public void addError(String message) {
        this.errorList.add(message);
    }
}
