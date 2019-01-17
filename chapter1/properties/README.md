# properties

## 配置文件  
SpringBoot使用一个全局的配置文件，配置文件名是固定的；
* application.properties 中文默认以ISO-8859-1方式编码，因此需要对中文内容进行重新编码
* application.yml

SpringApplication 类会搜索并加载 application.properties 文件来获取配置属性值。
springboot 启动时加载  application.properties的优先级
```
   1. 当前目录的/config子目录 
   2. 当前目录
   3. classpath 中的/config包
   4. classpath
   ```
上面的顺序也表示了该位置上包含的属性文件的优先级。  
优先级按照从高到低的顺序排列。
### 命令行启动设置：
使用 --spring.config.location参数指定属性文件搜索路径
使用 --spring.config.name参数指定配置文件的前缀
配合 --spring.profiles.active参数指定不同profile来区分环境  
eg：java -jar springboottest-0.0.1-SNAPSHOT.jar --spring.config.name=myproject --spring.config.location=file:./config8888/ --spring.profiles.active=dev

##配置文件参数读取

###注解方式读取

####@Value属性名，在属性名上添加该注解
   
   * 在默认路径下的文件applicarion.properties，直接使用@Value("${SERVER_URL") 注解获得文件中的参数值
   * 设置默认值的方式:@Value(“${key:defaultVlaue}”) 的形式进行设置。
   * 非默认配置路径下，需要在类上面添加`@PropertySource`注解用于加载配置文件，但该注解无法加载yml配置文件。 
   例如：非application.properties，eg：classpath:config/my.properties
   * `@PropertySource`注解如有多配置文件引用，若取两个配置文件中有相同属性名的值，则取值为最后一个配置文件中的值  
   
    //abc.properties文件不存在,验证ignoreResourceNotFound属性
    //加上encoding = "utf-8"属性防止中文乱码,不能为大写的"UTF-8
    @PropertySource(value = {"classpath:/config/propConfigs.properties","classpath:/config/abc.properties"},
            ignoreResourceNotFound = true,encoding = "utf-8")
    public class PropConfig {
    }

####@ConfigurationProperties(prefix = "city")

   * 直接读取在默认路径下的配置文件applicarion.properties
   * 新版本的@ConfigurationProperties没有了location属性，使用@PropertySource来指定非默认路径下的配置文件位置  
   * 对象方法中使用自动注入方式，将对象注入，调用get方法获取属性值
   * prefix设置key的前缀,prefix是key的公共部分
   * 读取配置文件中的集合时，集合必须初始化，如果找不到就是空集合，会报错
   
    //prefix设置key的前缀;
    @ConfigurationProperties(prefix = "city")
    public class City {
    }


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
### YAML 语法

yaml 官方网站：http://www.yaml.org

* 使用空格 Space 缩进表示分层，不同层次之间的缩进可以使用不同的空格数目，但是同层元素一定左对齐，即前面空格数目相同（不能使用 Tab，各个系统 Tab对应的 Space 数目可能不同，导致层次混乱）
* ‘#’表示注释，只能单行注释，从#开始处到行尾
* 破折号后面跟一个空格（a dash and space）表示列表
* 用冒号和空格表示键值对 key: value
* 字符串默认不用加上单引号或者双引号；
                    
      “”：双引号；不会转义字符串里面的特殊字符；特殊字符会作为本身想表示的意思
      name: “zhangsan \n lisi”：输出；zhangsan 换行 lisi
      ‘’：单引号；会转义特殊字符，特殊字符最终只是一个普通的字符串数据
      name: ‘zhangsan \n lisi’：输出；zhangsan \n lisi
                 
Sequence of Scalars  简单数据列表（List、Set）
```
- Mark McGwire  
- Sammy Sosa  
- Ken Griffey  
```

Mapping Scalars to Scalars 简单数据键值对以及注释
```
hr:  65    # Home runs
avg: 0.278 # Batting average
rbi: 147   # Runs Batted In

```
Mapping Scalars to Sequences 简单数据列表键值对
    
    american:
      - Boston Red Sox
      - Detroit Tigers
      - New York Yankees
    national:
      - New York Mets
      - Chicago Cubs
      - Atlanta Braves

Sequence of Mappings 键值对列表 

    -
      name: Mark McGwire
      hr:   65
      avg:  0.278
    - 
      name: Sammy Sosa
      hr:   63
      avg:  0.288


YAML 还支持流类型，用中括号括起来表示列表，用逗号分隔元素；用大括号括起来表示键值对，用逗号分隔元素。
Sequence of Sequences 列表的列表 

   - [name        , hr, avg  ]
   - [Mark McGwire, 65, 0.278]
   - [Sammy Sosa  , 63, 0.288]

 Mapping of Mappings  键值对的键值对

    Mark McGwire: {hr: 65, avg: 0.278}
    Sammy Sosa: {
        hr: 63,
        avg: 0.288
      }