package com.sxm.springboot.common.enums;

/**
 * 定义异常时，需要先确定异常所属模块。
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/7/30 17:35
 * @since 0.1
 */
public enum ModuleCodeEnum {

    /**
     * 基础数据模块
     */
    BASIC_DATA_Module(1001, "基础数据模块"),//
    /**
     * 财务模块
     */
    FINANCE_Module(1002, "财务模块"), //
    /**
     * 运营模块
     */
    OPERATE_Module(1003, "运营模块"), //
    /**
     * 客服模块
     */
    CUSTOMER_Module(1004, "客服模块"),//
    /**
     * 接口模块
     */
    API_Module(1005, "接口模块");

    /**
     * 模块编码
     */
    private int moduleCode;
    /**
     * 模块名称
     */
    private String moduleName;

    ModuleCodeEnum(int moduleCode, String moduleName) {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
    }

    public int getModuleCode() {
        return moduleCode;
    }

}
