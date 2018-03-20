package com.sxm.springboot.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * 内嵌Tomcat 运行项目目录
 * 当目录路径不对的时候，可以使用。如果独立运行，不需要配置此类
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/9/9 9:08
 * @since 0.1
 */

//@Configuration
public class DocumentDirectoryCustomizer implements EmbeddedServletContainerCustomizer {
    public void customize(ConfigurableEmbeddedServletContainer container) {
        //项目目录
        container.setDocumentRoot(new File(System.getProperty("user.dir") + "/springboot-helloworld/src/main/webapp"));
    }
}