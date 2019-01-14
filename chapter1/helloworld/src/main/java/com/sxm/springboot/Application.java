package com.sxm.springboot;

import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * Spring Boot 应用启动类
 * 我们需要知道的是：Spring Boot默认情况下可以扫描到的是@SpringBootApplication所在的类的同包或者子包下的类。
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/7/26 11:28
 * @since 0.1
 */
// Spring Boot 应用的标识
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        // SpringApplication.run(HelloApplication.class, args);

        //自定义启动配置
        SpringApplication springApplication = new SpringApplication(Application.class);
        //关闭Banner
        springApplication.setBannerMode(Banner.Mode.OFF);
        //禁止命令行设置参数
        springApplication.setAddCommandLineProperties(false);
        springApplication.run(args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

        };
    }
}
