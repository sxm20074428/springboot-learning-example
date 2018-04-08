package com.sxm.springboot.exception;

import com.sxm.springboot.common.enums.ErrorCodeEnum;
import com.sxm.springboot.common.enums.ModuleCodeEnum;
import com.sxm.springboot.common.exception.BusinessJSONException;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/7/30 18:20
 * @since 0.1
 */
public class CityException extends BusinessJSONException {

    private static final long serialVersionUID = 1L;
    /**
     * 城市不存在
     */
    public static final CityException City_NotFound_Exception = new CityException(ModuleCodeEnum.BASIC_DATA_Module, ErrorCodeEnum.NOT_FOUND, "城市不存在");

    public CityException(ErrorCodeEnum errorCodeEnum, String message) {
        super(ModuleCodeEnum.BASIC_DATA_Module, errorCodeEnum, message);
    }

    public CityException(ModuleCodeEnum moduleCodeEnum, ErrorCodeEnum errorCodeEnum, String message) {
        super(moduleCodeEnum, errorCodeEnum, message);
    }
}
