# springboot-helloworld

##一、项目运行方式：
    
    在idea中，直接运行HelloApplication.java 文件。
    把项目通过maven命令 mvn package 打成jar包，通过命令 java -jar springboot-helloworld 1.0.0-SNAPSHOT.jar 直接执行jar包
    借助maven插件，执行mvn spring-boot:run 即可运行项目。


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

## 四、应用devtools进行热部署
1、devtools简介

    devtools是boot的一个热部署工具，当我们修改了类文件、属性文件、页面、配置文件等时，会重新启动程序。

    其原理是使用了两个ClassLoader，一个Classloader加载那些不会改变的类(第三方jar)，另一个ClassLoader加载会更改的类，成为restart ClassLoader。

    这样在有代码更改时候，原来的restart ClassLoader被丢弃，重新创建一个restart ClassLoader，由于需要加载的类比较少，所以实现了较快的重启时间（一般5秒内）。

2、使用方法

配置pom.xml添加依赖包
```
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-devtools</artifactId>
		<optional>true</optional>
	</dependency>
```
maven中的optional=true表示依赖不会传递。即此处引用的devtools不会传递到依赖此项目的项目中。  
仅仅加入devtools在我们的eclipse中还不起作用，这时候还需要对之前添加的spring-boot-maven-plugin做一些修改,如下：
```
   <build>
      <plugins>
	    <plugin>
	       <groupId>org.springframework.boot</groupId>
	       <artifactId>spring-boot-maven-plugin </artifactId>
	       <configuration>     
               <!-- 如果没有该项配置,devtools不会起作用 -->        
		   <fork>true</fork>
	       </configuration>
	    </plugin>
      </plugins>
   </build>
 ```
 
 