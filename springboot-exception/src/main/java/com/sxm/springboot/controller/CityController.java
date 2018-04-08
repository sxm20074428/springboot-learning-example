package com.sxm.springboot.controller;

import com.sxm.springboot.common.dto.ResultResponse;
import com.sxm.springboot.common.exception.BusinessException;
import com.sxm.springboot.domain.City;
import com.sxm.springboot.exception.CityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CityController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CityController.class);

    @GetMapping(value = "/cities/{id}")
    @ResponseBody
    public ResultResponse findOneCity(@PathVariable("id") int id) throws Exception {
        switch (id) {
            case 1:
                throw CityException.City_NotFound_Exception;
            case 2:
                City city = new City();
                city.setId(1L);
                city.setCityName("长沙市");
                city.setProvinceId(1L);
                city.setDescription("湖南省省会");
                return ResultResponse.createSuccessResponse(city);
            case 3:
                throw BusinessException.SYSTEM_EXCEPTION;
            default:
                throw new Exception("Generic Exception, id=" + id);
        }
    }

}
