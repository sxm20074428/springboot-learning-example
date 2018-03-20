# springboot-static

## 自定义静态资源映射
如果进入SpringMVC的规则为/时，Spring Boot的默认静态资源寻找的路径顺序为：  
```
spring.resources.static-locations=classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/
```
### 第1种方式：静态资源配置类
继承WebMvcConfigurerAdapter来实现
```  
@Configuration //申明这是一个配置
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //将所有/static/** 访问都映射到classpath:/static/ 目录下
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

    }
}
```  
重启项目，访问：http://localhost/static/img/b.png 即可访问到static/img目录下的b.png图片资源。 
         同样，http://localhost/a.png 等其他路径都可以和之前一样，按照默认静态资源寻找的路径

### 第2种方式：在application.properties配置
在application.properties中添加配置：
```
  spring.mvc.static-path-pattern=/static/**
```  
重启项目，访问：http://localhost/static/img/b.png 同样能正常访问static目录下的b.png图片资源。
         但是，http://localhost/img/a.png 等其他路径不可以访问

注意：通过spring.mvc.static-path-pattern这种方式配置，会使Spring Boot的默认配置失效，
也就是 说，/public /resources 等默认配置不能使用，只能通过/static/来访问。

## Spring Boot 配置
 WebMvcAutoConfiguration 这个类，它为 Spring Boot 提供了大量的 Web 服务的默认配置。这些配置包括但不局限于：设置了主页、webjars配置、静态资源位置等。这些配置对于我们使用配置 Web 服务很有借鉴意义。  
 注意：想要使用默认配置，无需使用 @EnaleWebMvc 注解。使用了 @EnableWebMvc 注解后 WebMvcAutoConfiguration 提供的默认配置会失效，必须提供全部配置。
 ```
 最后，可以通过使用 spring boot 提供的编写配置文件的方式，实现缓存，版本，以及资源隐射，压缩等功能。 简化了代码的编写
  ```
