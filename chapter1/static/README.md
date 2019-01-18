# 静态资源处理

以前做过web开发的同学应该知道，我们以前创建的web工程下面会有一个webapp的目录，我们只要把静态资源放在该目录下就可以直接访问。
但是，基于Spring boot的工程并没有这个目录，那我们应该怎么处理？

##一、最笨的方式
我们首先来分享一种最笨的办法，就是将静态资源通过流直接返回给前端，我们在maven工程的resources的根目录下建立一个html的目录，
然后我们把html文件放在该目录下，并且规定任何访问路径以/static/开头的即访问该目录下的静态资源，其实现如下：
```
@Controller
public class StaticResourceController {
 
    @RequestMapping("/static/**")
    public void getHtml(HttpServletRequest request, HttpServletResponse response) {
        String uri = request.getRequestURI();
        String[] arr = uri.split("static/");
        String resourceName = "index.html";
        if (arr.length > 1) {
            resourceName = arr[1];
        }
        String url = StaticResourceController.class.getResource("/").getPath() + "html/" + resourceName;
        try {
            FileReader reader = new FileReader(new File(url));
            BufferedReader br = new BufferedReader(reader);
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }
            response.getOutputStream().write(sb.toString().getBytes());
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

其实现过程很简单，就是先从路径中分离出来资源uri，然后从static目录下读取文件，并输出到前端。
因为只做简单演示，所以这里只处理了文本类型的文件，图片文件可以做类似的处理。
当然，我们在实际中肯定不会这么做，Spring boot也肯定有更好的解决办法。
不过这个办法虽然有点笨，但确是最本质的东西，无论框架如何方便的帮我们处理了这类问题，
但是抛开框架，我们依然要能够熟练的写出一个web项目，只有知道其实现原理，你才会在遇到问题时能得心应手。
现在我们再来看看Spring boot对静态资源的支持。

##二、Spring boot默认静态资源访问方式
By default Spring Boot uses /static directory in the classpath for static resources.  
If we run our project using executable JAR then we must not keep our static resources in src/main/webapp path 
because when JAR is packaged, it will be silently ignored by most of the build tools.  
The path src/main/webapp can be used when we only want to package project as WAR file.  

　　Spring boot默认对/**的访问可以直接访问四个目录下的文件：
   * classpath:/public/
   * classpath:/resources/
   * classpath:/static/
   * classpath:/META-INFO/resouces/

如果进入SpringMVC的规则为/时，Spring Boot的默认静态资源寻找的路径顺序为：  
```
spring.resources.static-locations=classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/
```
static-locations的默认值顺序可以调整


##Using Static Content

By default static resources are mapped on /** but we can change it as required using the following property.  
For example to relocate all resources to /resources/**, we can achieve it as following.  

    spring.mvc.static-path-pattern=/resources/** 
Let us discuss how to use our static resources. 


## 三、自定义静态资源目录

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
@Configuration标识一个配置类，这个在前面的文章中提到过多次。
WebMvcConfigurerAdapter是Spring提供的一个配置mvc的适配器，里面有很多配置的方法，addResourceHandlers就是专门处理静态资源的方法

重启项目，访问：http://localhost/static/img/b.png 即可访问到static/img目录下的b.png图片资源。 
         同样，http://localhost/img/a.png 等其他路径都可以和之前一样，按照默认静态资源寻找的路径

### 第2种方式：在application.properties配置
在application.properties中添加配置：

 * static-path-pattern：访问模式，默认为/**，多个可以逗号分隔
 
       spring.mvc.static-path-pattern=/static/**
                                           
 * static-locations：资源目录，多个目录逗号分隔，默认资源目录为classpath:/META-INF/resources/,classpath:/resources/,classpath:/static/,classpath:/public/
       
       spring.resources.static-locations=classpath:/resources/,classpath:/static/,classpath:/public/

重启项目，访问：http://localhost/static/img/b.png 同样能正常访问static目录下的b.png图片资源。
         但是，http://localhost/img/a.png 等其他路径不可以访问

注意：通过spring.mvc.static-path-pattern这种方式配置，会使Spring Boot的默认配置失效，
也就是说，/public /resources 等默认配置不能直接访问使用，只能通过/static/来访问。

## Spring Boot 配置
 WebMvcAutoConfiguration 这个类，它为 Spring Boot 提供了大量的 Web 服务的默认配置。 
 这些配置包括但不局限于：设置了主页、webjars配置、静态资源位置等。这些配置对于我们使用配置 Web 服务很有借鉴意义。  
 注意：想要使用默认配置，无需使用 @EnaleWebMvc 注解。
 使用了 @EnableWebMvc 注解后 WebMvcAutoConfiguration 提供的默认配置会失效，必须提供全部配置。
 ```
 最后，可以通过使用 spring boot 提供的编写配置文件的方式，实现缓存，版本，以及资源隐射，压缩等功能。 简化了代码的编写
  ```
