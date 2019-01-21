# devtools（热部署）
问题的提出：    
通过使用springloaded进行热部署，但是些代码修改了，并不会进行热部署。

##devtools简介

    spring-boot-devtools 是一个为开发者服务的一个模块，其中最重要的功能就是自动应用代码更改到最新的App上面去。
    
    devtools是boot的一个热部署工具，当我们修改了类文件、属性文件、页面、配置文件等时，会重新启动程序。

    其原理是使用了两个ClassLoader，一个Classloader加载那些不会改变的类(第三方jar)，另一个ClassLoader加载会更改的类，成为restart ClassLoader。

    这样在有代码更改时候，原来的restart ClassLoader被丢弃，重新创建一个restart ClassLoader，由于需要加载的类比较少，所以实现了较快的重启时间（一般5秒内）。

##使用方法

1、配置pom.xml添加依赖包
```
<!-- spring boot devtools 依赖包. -->  
<dependency>  
    <groupId>org.springframework.boot</groupId>  
    <artifactId>spring-boot-devtools</artifactId>  
    <!-- optional=true,依赖不会传递，该项目依赖devtools；之后依赖该项目的项目如果想要使用devtools，需要重新引入 -->
    <optional>true</optional>  
</dependency>  
```
maven中的optional=true表示依赖不会传递。即此处引用的devtools不会传递到依赖此项目的项目中。  

2、添加spring-boot-maven-plugin：

仅仅加入devtools在我们的开发工具中还不起作用，这时候还需要对之前添加的spring-boot-maven-plugin做一些修改,如下：
```
 <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <!-- 如果没有该项配置，devtools不会起作用，即应用不会restart -->
                    <fork>true</fork>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

当我们修改了java类后，IDEA默认是不自动编译的，而spring-boot-devtools又是监测classpath下的文件发生变化才会重启应用，所以需要设置IDEA的自动编译：  
（1）File-Settings-Compiler-Build Project automatically
（2）ctrl + shift + alt + /,选择Registry,勾上 Compiler autoMake allow when app running


如果不能使用的话，以下就几种常见的问题：

    1、对应的spring-boot版本是否正确，这里使用的是1.5.12版本；
    2、是否加入plugin以及属性<fork>true</fork>
    3、Eclipse Project 是否开启了Build Automatically（开启自动编译的功能）。
    4、如果设置SpringApplication.setRegisterShutdownHook(false)，则自动重启将不起作用。

最后，这两种方式springloader、devtools只需要配置一种即可，建议使用devtools，可以支持更多的代码热部署。

补充：
 * devtools可以实现页面热部署（即页面修改后会立即生效，这个可以直接在application.properties文件中配置spring.thymeleaf.cache=false来实现(这里注意不同的模板配置不一样)
 * 默认情况下，/META-INF/maven，/META-INF/resources，/resources，/static，/templates，/public这些文件夹下的文件修改不会使应用重启，但是会重新加载（devtools内嵌了一个LiveReload server，当资源发生改变时，浏览器刷新）
 * 如果想改变默认的设置，可以自己设置不重启的目录：spring.devtools.restart.exclude=static/**,public/**，这样的话，就只有这两个目录下的文件修改不会导致restart操作了。
 *  如果要在保留默认设置的基础上还要添加其他的排除目录：spring.devtools.restart.additional-exclude
 * 如果想要使得当非classpath下的文件发生变化时应用得以重启，使用：spring.devtools.restart.additional-paths，这样devtools就会将该目录列入了监听范围