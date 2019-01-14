# springboot-helloworld

##Spring Boot MVC Configuration
To enable Spring Boot MVC we need to use following starter in build file.
```
spring-boot-starter-web 
```
When Spring Boot scans Spring Web in classpath, it atomically configures Spring Web MVC.  
To change any configuration, Spring Boot provides properties to be configured in `application.properties`.  
Find some properties. 
`spring.mvc.async.request-timeout`: Timeout in milliseconds for asynchronous request. 
`spring.mvc.date-format`: Date format to use. 
`spring.mvc.favicon.enabled`: It enables and disables favicon. Default is true. 
`spring.mvc.locale`: Locale to use. 
`spring.mvc.media-types.*`: Maps file extensions to media type for content negotiation. 
`spring.mvc.servlet.load-on-startup`: It configures startup priority for Spring Web Services Servlet. Default value is -1. 
`spring.mvc.static-path-pattern`: It configures path pattern for static resources. 
`spring.mvc.view.prefix`: It configures prefix for Spring view such as JSP. 
`spring.mvc.view.suffix`: It configures view suffix. 

To take a complete control on Spring MVC configuration we can create a configuration class annotated with `@Configuration` and `@EnableWebMvc`.  
To override any settings we need to extend `WebMvcConfigurerAdapter` class.

##Using custom Favicon
For favicon, Spring Boot looks for `favicon.ico` in the configured static content location.  
To change default favicon, just put your `favicon.ico` file in that location.  
To enable and disable favicon we need to configure spring.mvc.favicon.enabled in application.properties.  
Default value is true. 

## 项目说明

`@SpringBootApplication` is a convenience annotation that adds all of the following:

* `@Configuration` tags the class as a source of bean definitions for the application context.
* `@EnableAutoConfiguration` tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
* Normally you would add `@EnableWebMvc` for a Spring MVC app, but Spring Boot adds it automatically when it sees spring-webmvc on the classpath. 
This flags the application as a web application and activates key behaviors such as setting up a DispatcherServlet.
* `@ComponentScan` tells Spring to look for other components, configurations, and services in the hello package, allowing it to find the controllers.

The main() method uses Spring Boot’s `SpringApplication.run()` method to launch an application. 
Did you notice that there wasn’t a single line of XML? No web.xml file either. 
This web application is 100% pure Java and you didn’t have to deal with configuring any plumbing or infrastructure.

There is also a `CommandLineRunner` method marked as a `@Bean` and this runs on start up.  
It retrieves all the beans that were created either by your app or were automatically added thanks to Spring Boot. It sorts them and prints them out.


##一、项目运行方式：

 我们需要知道的是：Spring Boot默认情况下可以扫描到的是@SpringBootApplication所在的类的同包或者子包下的类。

###开发环境    

1. 在idea中，直接运行HelloApplication.java 文件。  
2. 借助maven插件，执行mvn spring-boot:run 即可运行项目。
```
mvn spring-boot:run
```

###生产环境
   
1. 把项目通过maven命令 mvn package 打成jar包，通过命令 java -jar helloworld 1.0.0-SNAPSHOT.jar 直接执行jar包  
    但是当然你关闭当前的xshell 命令界面时，再次访问就失效了，这是因为springboot内置的tomcat随之也关闭了

2. nohup java -jar helloworld.jar &
   
   nohup常驻的意思，不担心关闭xshell,只要云服务器不关闭，总能访问的  
   最后一个&表示执行命令后要生成日志文件nohup.out
   

## 二、单元测试
If you are using Maven, add this to your list of dependencies:
```
 <dependency>
     <groupId>org.springframework.boot</groupId>
     <artifactId>spring-boot-starter-test</artifactId>
     <scope>test</scope>
 </dependency>
```        
spring boot 1.4.0 版本之前使用以下三个配置
```
@RunWith(SpringJUnit4ClassRunner.class)
//在spring boot 1.4.0 版本之前classes需要指定spring boot 的启动类，如：DemoApplication.class 不然WebApplicationContext不被实例化
@SpringApplicationConfiguration(classes = DemoApplication.class)
@WebAppConfiguration
```  
 spring boot 1.4.0 版本之后使用以下两个配置
```
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
```  
参见;`HelloWorldControllerTest1``HelloWorldControllerTest2`  
The `MockMvc` comes from Spring Test and allows you, via a set of convenient builder classes, 
to send HTTP requests into the `DispatcherServlet` and make assertions about the result. 
Note the use of the `@AutoConfigureMockMvc` together with `@SpringBootTest` to inject a `MockMvc` instance. 
Having used `@SpringBootTes`t we are asking for the whole application context to be created. 
An alternative would be to ask Spring Boot to create only the web layers of the context using the `@WebMvcTest`. 
Spring Boot automatically tries to locate the main application class of your application in either case, 
but you can override it, or narrow it down, if you want to build something different.

参见:`com.sxm.springboot.HelloWorldControllerTest3`  
As well as mocking the HTTP request cycle we can also use Spring Boot to write a very simple full-stack integration test. 
For example, instead of (or as well as) the mock test above we could do this: 

The embedded server is started up on a random port by virtue of the `webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT` and the actual port is discovered at runtime with the @LocalServerPort.