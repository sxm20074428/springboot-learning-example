package com.sxm.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * LazyInit is a boolean flag that indicates whether all discovered beans should be created by the container eagerly when it starts or when they are accessed for the first time.
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/4/18 0018 上午 10:01
 * @since 0.1
 */
@SpringBootApplication
public class LazyApplication {

    public static void main(String[] args) {
        SpringApplication.run(LazyApplication.class, args);
    }

    /**
     * he class is annotated with @Profile so that it is activated only if the local profile is enabled.
     * If the local profile is activated, the lazyInit in @ComponentScan on the LocalConfig class overrides the default value provided by the @SpringBootApplication annotation on the main class.
     * If the local profile is deactivated, the LocalConfig class is ignored and the defaults are used.
     *
     * @author 苏晓蒙
     * @version 0.1
     * @time 2018/4/18 0018 上午 10:48
     * @since 0.1
     */
    @Configuration
    @Profile("local")
    @ComponentScan(lazyInit = true)
    static class LocalConfig {
    }

}
