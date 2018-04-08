# springboot-exception

## ControllerAdvice

1、ControllerAdvice简介

在spring 3.2中,新增了@ControllerAdvice 注解可以用于定义@ExceptionHandler、@InitBinder、@ModelAttribute,并应用到所有@RequestMapping中。

2、设置@ControllerAdvice应用范围

设置了@ControllerAdvice应用范围，即就设置了异常类的应用范围

@ControllerAdvice的范围有：
```
basePackages：应用在xx包

basePackageClasses：应用在xx类

assignableTypes：应用在加了@Controller的类

annotations：应用在带有xx注解的类或者方法

```

简单用法例子：
```
@ControllerAdvice(basePackages={"com.springboot.controller"}) 只捕捉com.springboot.controller包中的异常  

@ControllerAdvice(basePackageClasses={TestController.class}) 只捕捉TestController.class中的异常  

@ControllerAdvice(assignableTypes={TestController.class})  只捕捉TestController.class中的异常  

@ControllerAdvice(annotations=TestException.class) 只捕捉带有@TestException注解的类  

```
上面四个注解一个应用包，然后的两个用在类，而最后一个只应用于带有XX注解的类