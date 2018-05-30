package com.sxm.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p>一个简单的使用演示</p>
 * <p>1.按需配置application.properties，配置项请参考config-template.properties</p>
 * <p>2.run DemoApplication</p>
 * <p>3.访问http://127.0.0.1:8080/druid</p>
 * <p>4.访问/user/${id}接口，查看SQL、Web、AOP监控效果，如：http://127.0.0.1:8080/user/1</p>
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
