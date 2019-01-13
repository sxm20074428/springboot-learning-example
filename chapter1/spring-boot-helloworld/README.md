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

##一、项目运行方式：

 我们需要知道的是：Spring Boot默认情况下可以扫描到的是@SpringBootApplication所在的类的同包或者子包下的类。

Build and run the app using maven
```
cd springboot-helloworld
mvn package
java -jar target/springboot-helloworld-0.0.1-SNAPSHOT.jar
```
You can also run the app without packaging it using -
```
mvn spring-boot:run
```

###开发环境    

1. 在idea中，直接运行HelloApplication.java 文件。  
2. 借助maven插件，执行mvn spring-boot:run 即可运行项目。

###生产环境
   
1. 把项目通过maven命令 mvn package 打成jar包，通过命令 java -jar springboot-helloworld 1.0.0-SNAPSHOT.jar 直接执行jar包  
    但是当然你关闭当前的xshell 命令界面时，再次访问就失效了，这是因为springboot内置的tomcat随之也关闭了

2. nohup java -jar helloworld.jar &
   
   nohup常驻的意思，不担心关闭xshell,只要云服务器不关闭，总能访问的  
   最后一个&表示执行命令后要生成日志文件nohup.out
   

## 二、单元测试
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
```  

 
 