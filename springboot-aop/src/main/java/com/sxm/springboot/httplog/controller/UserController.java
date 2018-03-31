package com.sxm.springboot.httplog.controller;

import com.sxm.springboot.common.BaseResponse;
import com.sxm.springboot.httplog.annotation.HttpLog;
import com.sxm.springboot.httplog.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {


    /**
     * curl -H 'username:12b4' -H 'password:34ndd' -v 'http://localhost:8080/user/log/123?age=32'
     */
    @GetMapping("/log/{id}")
    @HttpLog()
    public BaseResponse<User> getInfo(@PathVariable("id") int id, @RequestParam("age") int age) {
        User user = new User();
        user.setId(id);
        user.setUsername(String.valueOf(new Random().nextLong()));
        user.setAge(age);
        return BaseResponse.createSuccessResponse(user);
    }

    /**
     * curl -H 'username:12b4' -H 'password:34ndd' -v 'http://localhost:8080/user/log/pwd/123?age=32'
     */
    @GetMapping("/log/pwd/{id}")
    @HttpLog(headerParams = "password")
    public BaseResponse<User> getInfoWithPwd(@PathVariable("id") int id, @RequestHeader("username") String username, @RequestHeader("password") String password) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        return BaseResponse.createSuccessResponse(user);
    }

    /**
     * curl -H 'username:12b4' -H 'password:34ndd' -v 'http://localhost:8080/user/log/pwdExcludeResponse/123?age=32'
     */
    @GetMapping("/log/pwdExcludeResponse/{id}")
    @HttpLog(headerParams = "username", ignoreResponse = true)
    public BaseResponse<User> getInfoWithPwd(@PathVariable("id") int id, @RequestParam("age") int age, @RequestHeader("password") String password) {
        User user = new User();
        user.setId(id);
        user.setPassword(password);
        user.setAge(age);
        return BaseResponse.createSuccessResponse(user);
    }

}
