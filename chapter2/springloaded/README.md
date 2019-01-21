#Spring Boot热部署(springloader)
问题的提出：  
在编写代码的时候，你会发现我们只是简单把打印信息改变了，就需要重新部署，如果是这样的编码方式，那么我们估计一天下来就真的是打几个Hello World就下班了。  
那么如何解决热部署的问题呢？那就是springloaded

##使用方法：
* 首先配置maven plugin
```
 <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <dependencies>
                    <!--  mvn spring-boot:run 热部署启动 -->
                    <dependency>
                        <groupId>org.springframework</groupId>
                        <artifactId>springloaded</artifactId>
                        <version>1.2.8.RELEASE</version>
                    </dependency>
                </dependencies>
            </plugin>
        </plugins>
    </build>  
```
###运行方法一
* 在IDE里面运行spring-boot:run

   [INFO] Attaching agents: [F:\server\maven\repository\org\springframework\springloaded\1.2.8.RELEASE\springloaded-1.2.8.RELEASE.jar]
   如果你能在控制台上看到这条日志说明spring-loaded已经加载了，按道理就是可以正常work了。

###运行方法二
如果使用的`run as – java application`的话，那么还需要做一些处理。

把spring-loader-1.2.8.RELEASE.jar下载下来，放到项目的lib目录中，然后把IDEA的run参数里VM参数设置为：  
`-javaagent:.\lib\springloaded-1.2.4.RELEASE.jar -noverify`  
然后启动就可以了，这样在run as的时候，也能进行热部署


* 提示

当我们修改了java类后，IDEA默认是不自动编译的，所以需要设置IDEA的自动编译：    
  （1）File-Settings-Compiler-Build Project automatically  
  （2）ctrl + shift + alt + /,选择Registry,勾上 Compiler autoMake allow when app running  

你可以直接快捷键触发：   Ctrl+Shift+F9  

另一种方法：
      
  为了方便，最好设置成保存的时候就自动编译，方法可能有许多，我这里采用的是Macros方式来录制宏的：
```
1. Edit -> Macros -> Start Macro Recording
2. Click File -> Save All
3. Cilck Build -> Make Project
4. Edit -> Macros -> Stop Macro Recording
```
保存你录制的Macro，例如叫SaveAnd  
接下来，为了能够在Ctrl+S的时候不仅保存还触发编译，我们先remove原有的keymap，修改SaveAndMake的快捷键为Ctrl+S。       

* 补充
springboot 2 版本不兼容，无法使用
这两种方式springloader、devtools只需要配置一种即可，建议使用devtools，可以支持更多的代码热部署。