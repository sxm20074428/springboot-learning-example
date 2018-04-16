package com.sxm.springboot.common.exception;

import com.sxm.springboot.common.enums.ErrorCodeEnum;
import com.sxm.springboot.common.enums.ModuleCodeEnum;

/**
 * 默认响应的是json字符串给客户端
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/7/30 17:35
 * @since 0.1
 */
public class BusinessJSONException extends BusinessException implements JSONInterface {

    public BusinessJSONException(ModuleCodeEnum moduleCodeEnum, ErrorCodeEnum errorCodeEnum, String message) {
        super(moduleCodeEnum, errorCodeEnum, message);
    }
}
