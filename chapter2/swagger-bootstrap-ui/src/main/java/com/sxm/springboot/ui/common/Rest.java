package com.sxm.springboot.ui.common;

import io.swagger.annotations.ApiModelProperty;

public class Rest<T> {

    @ApiModelProperty(value = "是否成功")
    private boolean success=true;

    @ApiModelProperty(value = "返回对象")
    private T data;

    @ApiModelProperty(value = "错误编号")
    private Integer errCode;

    @ApiModelProperty(value = "错误信息")
    private String message;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getErrCode() {
        return errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
