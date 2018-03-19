# springboot-properties

#### 一、属性配置文件  application.properties
SpringApplication 类会搜索并加载 application.properties 文件来获取配置属性值。
SpringApplication 类会在下面位置搜索该文件：
```
1. 当前目录的/config子目录
2. 当前目录
3. classpath 中的/config包
4. classpath
```
上面的顺序也表示了该位置上包含的属性文件的优先级。  
优先级按照从高到低的顺序排列。

可以通过spring.config.name这个key的配置属性来指定不同的属性文件名称。  
也可以通过spring.config.location来添加额外的属性文件的搜索路径

#### 二、属性配置文件  application.yml（推荐）
 
相对于属性文件来说，YAML 是一个更好的配置文件格式。当有前缀的情况下，使用.yml格式的配置文件更简单。

`注意`：使用.yml时，属性名的值和冒号中间`必须`有空格，如name: SpringBoot正确，SpringBoot就是错的。

YAML 在 Ruby on Rails 中得到了很好的应用。  
YAML是JSON的一个超集，也是一种方便的定义层次配置数据的格式。它的基本语法规则如下：
```
大小写敏感
使用缩进表示层级关系
缩进时不允许使用Tab键，只允许使用空格。
缩进的空格数目不重要，只要相同层级的元素左侧对齐即可
# 表示注释，从这个字符一直到行尾，都会被解析器忽略。
```


#### 三、配置方式和优先级
这些方式优先级如下：
```
a. 命令行参数
b. 来自java:comp/env的JNDI属性
c. Java系统属性（System.getProperties()）
d. 操作系统环境变量
e. RandomValuePropertySource配置的random.*属性值
f. jar外部的application-{profile}.properties或application.yml(带spring.profile)配置文件
g. jar内部的application-{profile}.properties或application.yml(带spring.profile)配置文件
h. jar外部的application.properties或application.yml(不带spring.profile)配置文件
i. jar内部的application.properties或application.yml(不带spring.profile)配置文件
j. @Configuration注解类上的@PropertySource
k. 通过SpringApplication.setDefaultProperties指定的默认属性
可见，命令行参数优先级最高。这个可以根据这个优先级，可以在测试或生产环境中快速地修改配置参数值，而不需要重新打包和部署应用。
```
SpringApplication 类默认会把以“--”开头的命令行参数转化成应用中可以使用的配置参数，如 “--name=Alex” 会设置配置参数 “name” 的值为 “Alex”。  

如果不需要这个功能，可以通过SpringApplication.setAddCommandLineProperties(false)禁用解析命令行参数。


spring boot set application.properties from file
```
@SpringBootApplication
public class AppTaskApplication {

    public static void main(String[] args) {
        try {
            String rootPath = ConfigUtils.getResourcesRootPath();
            String fileFullName = rootPath + "application.properties";
            Properties properties = new Properties();
            properties.load(new FileInputStream(fileFullName));

            SpringApplication application = new SpringApplication(AppTaskApplication.class);
            application.setDefaultProperties(properties);
            application.run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

public class ConfigUtils {
    public static String getRootPath() throws Exception {
        String path = ConfigUtils.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        if (path.startsWith("/")) {
            path = path.substring(1, path.length());
        }
        int indexOfJar = path.indexOf(".jar");
        if (indexOfJar > -1) {
            String folder = path.substring(0, path.lastIndexOf('/')) + "/";
            return folder;
        } else {
            int index = path.indexOf("classes/");
            if (index > 0) {
                String result = path.substring(0, index);
                return result;
            }
            return path;
        }
    }

    public static String getResourcesRootPath() throws Exception {
        return getRootPath() + "resources/";
    }
}
```
