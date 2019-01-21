package com.sxm.springboot.ui.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger 配置文件
 * swagger 默认会扫描所有接口,实现Spring 配置文件中的 springfox.documentation.swagger2.configuration.Swagger2DocumentationConfiguration 类,控制只显示实现了ApiOperation 接口的API
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/8/29 16:31
 * @since 0.1
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)//
                .groupName("v1") //分组
                .apiInfo(apiInfo())//
                .select()//
                .apis(RequestHandlerSelectors.basePackage("com.sxm.springboot.ui.controller"))//
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))//
                .paths(PathSelectors.ant("/api/v1/**"))//版本分组规则
                .build();//
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()//
                .title("swagger-ui及swagger-bootstrap-ui RESTful APIs")//
                .description("swagger-bootstrap-ui 是Swagger的前端UI实现,目的是替换Swagger默认的UI实现Swagger-UI,使文档更友好一点儿...." + "</br>" + "swagger-bootstrap-ui 只是Swagger的UI实现,并不是替换Swagger功能,所以后端模块依然是依赖Swagger的,需要配合Swagger的注解达到效果,注解说明")//
                .termsOfServiceUrl("http://localhost/")//
                .contact(new Contact("苏晓蒙", "", "895219077@qq.com")).version("1.0")//
                .build();
    }

    @Bean
    public Docket api2() {
        return new Docket(DocumentationType.SWAGGER_2)//
                .groupName("v2")//
                .apiInfo(apiInfo())
                .select()//
                .apis(RequestHandlerSelectors.any())//
                .paths(PathSelectors.ant("/api/v2/**"))//版本分组规则
                .build();
    }

}
