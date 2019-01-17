package com.sxm.springboot.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot HelloWorld 案例
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/")
    public String index() {
        return "Hello";
    }

}
