# spring-boot-mail

邮件发送服务，文本，附件，模板，队列，多线程，定时任务实现多种功能！！！

[SpringBoot开发案例之整合mail发送服务](https://blog.52itstyle.com/archives/1264/)

[SpringBoot开发案例之整合mail队列篇](https://blog.52itstyle.com/archives/1385/)

## 开发环境

JDK1.7、Maven、Eclipse、SpringBoot1.5.10、spring-boot-starter-mail、spring-boot-starter-thymeleaf，spring-boot-starter-freemarker

## 流程图

### 平台架构
![输入图片说明](https://git.oschina.net/uploads/images/2017/0801/190708_991f282a_87650.png "2574887637.png")

### 进程内邮件队列
![邮件队列](https://git.oschina.net/uploads/images/2017/0804/135111_3b197795_87650.png "邮件队列.png")


- 普通文本发送
- 富文本发送(图片、附件)
- freeMarker模版发送邮件
- thymeleaf模版发送邮件

# 评测生成模版时间对比(1000次循环)


- Thymeleaf用时:2686ms
- Freemarker用时:498ms

对比测试，建议使用Freemarker模版

注意：springboot 1.4.0以后 Velocity 废弃了，官方建议用freemaker。

其他：created with a Thymeleaf template. The whole step-by-step tutorial with explanations can be found in [the blog post](http://dolszewski.com/spring/sending-html-mail-with-spring-boot-and-thymeleaf/).
