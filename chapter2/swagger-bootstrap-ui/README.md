# swagger演示项目

##What is Swagger?

Swagger(Swagger 2) is a specification for describing and documenting a REST API.  
It specifies the format of the REST web services including URL, Resources, methods, etc.  
Swagger will generate documentation from the application code and handle the rendering part as well.

In this post, I am going to integrate Swagger 2 documentation into a Spring Boot based REST web service.  
So I am going to use Springfox implementation to generate the swagger documentation.  
If you want to know how to run/build Spring Boot project, please refer my previous post.

Springfox provides two dependencies to generate API Doc and Swagger UI.  
If you are not expecting to integrate Swagger UI into your API level, no need to add  Swagger UI dependency.

```
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger2</artifactId>
    <version>2.7.0</version>
</dependency>

<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-swagger-ui</artifactId>
    <version>2.7.0</version>
</dependency>
```

@EnableSwagger2 annotation enables Springfox Swagger support in the class.  
To document the service, Springfox uses a Docket. The Docket helps to configure a subset of the services to be documented and group them by a name, etc.   
The most hidden concept is that the Springfox works by examining an application at runtime using API semantics based on spring configurations.   
In other words, you have to create a Spring Java Configuration class which uses spring’s `@Configuration`  

Out of the box, Springfox  provides five predicates and they are`any`, `none`, `withClassAnnotation`, `withMethodAnnotation` and `basePackage`.

###ApiInfo

Swagger provides some default values such as “API Documentation”, “Created by Contact Email”, “Apache 2.0”.   
So you can change these default values by adding apiInfo(ApiInfo apiInfo) method.   
The ApiInfo class contains custom information about the API.

### Controller and POJO Level Documentation

    @Api annotation is used to explain each rest controller class.
    @ApiOperation annotation is used to explain to describe the resources and methods.
    @ApiResponse annotation  is used to explain to describe other responses that can be returned by the operation.ex: 200 ok or 202 accepted, etc.
    @ApiModelProperty annotation to describe the properties of the POJO(Bean) class.
 
 
## swagger-bootstarp-ui

### 说明
swagger-bootstrap-ui是Swagger的前端UI实现,目的是替换Swagger默认的UI实现Swagger-UI,使文档更友好一点儿....

swagger-bootstrap-ui 只是Swagger的UI实现,并不是替换Swagger功能,所以后端模块依然是依赖Swagger的,需要配合Swagger的注解达到效果,注解说明

swagger-bootstrap-ui的jar包已经上传到maven中央仓库,可以直接使用，目前的版本是1.7.3

[中央仓库地址](http://mvnrepository.com/artifact/com.github.xiaoymin/swagger-bootstrap-ui)

maven配置

```
java
<dependency>
    <groupId>com.bycdao.cloud</groupId>
    <artifactId>swagger-bootstrap-ui</artifactId>
    <version>1.7.3</version>
</dependency>
```

1、该项目是Spring boot项目,直接main方法运行`Application`即可

2、端口是`application.properties`中配置的80
```
server.port=80
```

3、访问地址
    
  swagger-ui默认访问地址是：`http://${host}:${port}/swagger-ui.html`  
  
  swagger-bootstrap-ui默认访问地址是：`http://${host}:${port}/doc.html` 
  
  swagger封装给出的请求地址默认是`/v2/api-docs`,所以swagger-bootstrap-ui调用后台也是`/v2/api-docs`,不能带后缀,且需返回json格式数据
  
  如果是spring boot的可以不用修改,直接使用  
  如果是Spring MVC在web.xml中配置了DispatcherServlet,则需要追加一个url匹配规则,如下：
 
 ```   
    <servlet>
       <servlet-name>cmsMvc</servlet-name>
       <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
       <init-param>
       <param-name>contextConfigLocation</param-name>
       <param-value>classpath:config/spring.xml</param-value>
       </init-param>
       <load-on-startup>1</load-on-startup>
    </servlet>
    <!--默认配置,.htm|.do|.json等等配置-->
    <servlet-mapping>
    	<servlet-name>cmsMvc</servlet-name>
     	<url-pattern>*.htm</url-pattern>
    </servlet-mapping>
    <!-- 配置swagger-bootstrap-ui的url请求路径-->
    <servlet-mapping>
       <servlet-name>cmsMvc</servlet-name>
       <url-pattern>/v2/api-docs</url-pattern>
    </servlet-mapping>
```

##鸣谢

特别感谢以下大牛开发的js/css、html前端框架，美观、易用

框架	|网站
:------:|:------:
jquery |	http://jquery.com/
bootstrap	| http://getbootstrap.com
layer	| http://layer.layui.com/
jsonview	| https://github.com/yesmeck/jquery-jsonview