package com.sxm.springboot.controller;

import com.sxm.springboot.domain.Demo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Spring Boot HelloWorld 案例
 * Spring Boot默认使用的json解析框架是jackson
 */
@RestController
public class FastjsonController {

    /**
     * Spring Boot默认使用的json解析框架是jackson
     *
     * @return
     */
    @RequestMapping("/")
    public Demo getDemo() {
        Demo demo = new Demo();
        demo.setId(1);
        demo.setName("张三");
        demo.setCreateTime(new Date());
        demo.setRemarks("这是备注信息");
        return demo;
    }

}
