package com.sxm.springboot.common.enums;

/**
 * 定义异常时，异常的常用错误码。
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/7/30 17:35
 * @since 0.1
 */
public enum ErrorCodeEnum {

    /**
     * 不存在
     */
    NOT_FOUND(404, "不存在"),//

    /**
     * 内部原因
     */
    INNER_ERROR(500, "内部原因");

    /**
     * 错误编码
     */
    private int errorCode;
    /**
     * 模块名称
     */
    private String desc;

    ErrorCodeEnum(int errorCode, String desc) {
        this.errorCode = errorCode;
        this.desc = desc;
    }

    public int getErrorCode() {
        return errorCode;
    }

}
