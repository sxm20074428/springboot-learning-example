package com.sxm.springboot;

import com.sxm.springboot.property.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;

/**
 * Spring Boot 应用启动类
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/7/26 11:28
 * @since 0.1
 */
// Spring Boot 应用的标识
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
public class Application implements CommandLineRunner {

    @Autowired
    private City city;

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(Application.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n" + city.toString());
        System.out.println();
    }

}
