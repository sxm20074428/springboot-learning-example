package com.sxm.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.GzipResourceResolver;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import java.util.concurrent.TimeUnit;

//@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/static/**") //将所有/static/** 访问都映射到classpath:/static/ 目录下
                .addResourceLocations("classpath:/static/")//
                .setCacheControl(CacheControl.maxAge(10, TimeUnit.MINUTES).cachePrivate())//Http 缓存
                .resourceChain(false) //开发时 关闭 chain cache,方便调试
                .addResolver(new GzipResourceResolver()) //gzip 压缩
                //使用 MD5 作为版本号，添加 VersionResourceResolver
                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));

    }
}
