package com.sxm.springboot.common.exception;


import com.sxm.springboot.common.enums.ErrorCodeEnum;
import com.sxm.springboot.common.enums.ModuleCodeEnum;

/**
 * 业务异常基类，针对预期的异常，需要在程序中抛出此类的异常,所有业务异常都必须继承于此异常
 * 定义异常时，需要先确定异常所属模块。 例如：无效用户可以定义为 [1001:0001]
 * 前四位数为系统模块编号，后4位为错误代码 ,唯一。
 * 如果与业务功能相关的异常，建议在service中抛出异常。
 * 与业务功能没有关系的异常，建议在controller中抛出。
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/7/30 17:35
 * @since 0.1
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * 系统内部异常
     */
    public static final BusinessException SYSTEM_EXCEPTION = new BusinessException(ModuleCodeEnum.FINANCE_Module, ErrorCodeEnum.INNER_ERROR, "系统内部异常");

    /**
     * 具体异常码
     */
    protected String errorCode;

    public String getErrorCode() {
        return errorCode;
    }

    public BusinessException(ModuleCodeEnum moduleCodeEnum, ErrorCodeEnum errorCodeEnum, String message) {
        super(message);
        this.errorCode = moduleCodeEnum.getModuleCode() + ":" + errorCodeEnum.getErrorCode();
    }


}
