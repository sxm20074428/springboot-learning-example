package com.sxm.springboot.controller;

import com.sxm.springboot.common.dto.ResultResponse;
import com.sxm.springboot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/{uid}")
    public ResultResponse getInfo(@Validated User user) {

        return ResultResponse.createSuccessResponse(user);

    }

}
