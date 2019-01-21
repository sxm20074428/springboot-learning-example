# 配置fastjon(支持两种方法)

##第一种方法就是：
  （1）启动类继承extends WebMvcConfigurerAdapter  
  （2）覆盖方法configureMessageConverters

##第二种方法
（1）在App.java启动类中注入Bean : HttpMessageConverters

 
 