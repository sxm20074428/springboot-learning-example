package com.sxm.springboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 在这里我们使用RestController  （等待于 @Controller 和 @RequestBody）
 */
@RestController
public class HelloWorldController {

    /**
     * 在这里我们使用@RequestMapping 建立请求映射:
     * Spring Boot默认使用的json解析框架是jackson
     *
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "Hello";
    }



}
