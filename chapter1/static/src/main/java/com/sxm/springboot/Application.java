package com.sxm.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot 应用启动类
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/7/26 11:28
 * @since 0.1
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
         SpringApplication.run(Application.class, args);

    }

}
