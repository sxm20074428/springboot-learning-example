package com.sxm.springboot.common.dto;

import com.sxm.springboot.common.exception.BusinessException;

import java.io.Serializable;

/**
 * Ajax响应
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/7/30 17:35
 * @since 0.1
 */
public class ResultResponse<T extends Serializable> implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 成功的状态code,1为成功
     */
    public static final int SUCCESS_CODE = 0x1;

    /**
     * 失败的状态code
     */
    public static final int ERROR_CODE = 0x2;

    /**
     * 结果状态码，必须字段，
     */
    private int code;

    /**
     * 异步结果数据，如果code不等于1，则该项可以为空
     */
    private T data;
    /**
     * 异步处理信息，可以为空
     */
    private String msg;

    /**
     * 构造函数，具有多个参数的构造函数
     *
     * @param code 结果状态码
     * @param data 异步结果数据
     * @param msg  异步处理信息
     */
    private ResultResponse(int code, T data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 构造错误结果的Ajax返回对象
     *
     * @param msg 操作提示消息
     * @return ajax对象信息
     */
    public static final ResultResponse createErrorResponse(String msg) {
        return new ResultResponse(ERROR_CODE, null, msg);
    }

    /**
     * 构造错误结果的Ajax返回对象
     *
     * @param exception 异常对象
     * @return ajax对象信息
     */
    public static final ResultResponse createErrorResponse(BusinessException exception) {
        return new ResultResponse(ERROR_CODE, null, exception.getMessage());
    }

    /**
     * 构造正确结果的Ajax返回对象
     *
     * @param data 数据对象
     * @param msg  操作提示消息
     * @return ajax对象信息
     */
    public static <T extends Serializable> ResultResponse createSuccessResponse(T data, String msg) {
        return new ResultResponse(SUCCESS_CODE, data, msg);
    }

    /**
     * 构造正确结果的Ajax返回对象
     *
     * @param data 数据对象
     * @return ajax对象信息
     */
    public static <T extends Serializable> ResultResponse createSuccessResponse(T data) {
        return new ResultResponse(SUCCESS_CODE, data, null);
    }


}
