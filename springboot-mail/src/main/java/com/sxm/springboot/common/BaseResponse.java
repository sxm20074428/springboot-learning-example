package com.sxm.springboot.common;

import java.io.Serializable;

/**
 * Ajax响应，数据响应，不包括行数

 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/7/30 17:35
 * @since 0.1
 */
public class BaseResponse implements Serializable {

    private static final long serialVersionUID = -1L;

    /**
     * 成功的状态code,1为成功
     */
    public static final int SUCCESS_CODE = 1;

    /**
     * 失败的状态code
     */
    public static final int ERROR_CODE = 2;

    /**
     * 结果状态码，必须字段，
     */
    private int code;

    /**
     * 异步结果数据，如果code不等于1，则该项可以为空
     */
    private Object data;

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
    public BaseResponse(int code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public Object getData() {
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
    public static final BaseResponse createErrorResponse(String msg) {
        return new BaseResponse(ERROR_CODE, null, msg);
    }

    /**
     * 构造错误结果的Ajax返回对象
     *
     * @param exception 异常对象
     * @return ajax对象信息
     */
    public static final BaseResponse createErrorResponse(Exception exception) {
        return new BaseResponse(ERROR_CODE, null, exception.getMessage());
    }

    /**
     * 构造正确结果的Ajax返回对象
     *
     * @param data 数据对象
     * @return ajax对象信息
     */
    public static <T extends Serializable> BaseResponse createSuccessResponse(Object data) {
        return new BaseResponse(SUCCESS_CODE, data, null);
    }

    /**
     * 构造正确结果的Ajax返回对象
     *
     * @param data 数据对象
     * @param msg  操作提示消息
     * @return ajax对象信息
     */
    public static <T extends Serializable> BaseResponse createSuccessResponse(Object data, String msg) {
        return new BaseResponse(SUCCESS_CODE, data, msg);
    }

}
