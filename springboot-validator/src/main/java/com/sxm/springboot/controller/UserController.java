package com.sxm.springboot.controller;

import com.sxm.springboot.common.dto.ResultResponse;
import com.sxm.springboot.domain.User;
import com.sxm.springboot.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{uid}")
    public ResultResponse getInfo(@Validated User user) {

        return ResultResponse.createSuccessResponse(user);

    }

    @PostMapping("/register")
    public ResultResponse register(@RequestBody @Valid User user) {
        userService.save(user);
        return ResultResponse.createSuccessResponse(user);

    }

}
