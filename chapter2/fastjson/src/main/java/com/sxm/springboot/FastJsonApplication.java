package com.sxm.springboot;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring Boot 应用启动类
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/7/26 11:28
 * @since 0.1
 */
@SpringBootApplication
public class FastJsonApplication
//        extends WebMvcConfigurerAdapter
{

//    	@Override
//    	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//    		super.configureMessageConverters(converters);
//
//    		/*
//    		 * 1、需要先定义一个 convert 转换消息的对象;
//    		 * 2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
//    		 * 3、在convert中添加配置信息.
//    		 * 4、将convert添加到converters当中.
//    		 *
//    		 */
//
//    		// 1、需要先定义一个 convert 转换消息的对象;
//    		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//
//    		//2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
//    		FastJsonConfig fastJsonConfig = new FastJsonConfig();
//    		fastJsonConfig.setSerializerFeatures(
//                    SerializerFeature.PrettyFormat
//            );
//
//    		//3、在convert中添加配置信息.
//            fastConverter.setFastJsonConfig(fastJsonConfig);
//
//            //4、将convert添加到converters当中.
//        	converters.add(fastConverter);
//
//    	}


    /**
     * 在这里我们使用 @Bean注入 fastJsonHttpMessageConvert
     *
     * @return
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 1、需要先定义一个 convert 转换消息的对象;
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

        //2、添加fastJson 的配置信息，比如：是否要格式化返回的json数据;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //3、在convert中添加配置信息.
        fastConverter.setFastJsonConfig(fastJsonConfig);

        //设置中文编码格式
        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_JSON_UTF8);
        fastConverter.setSupportedMediaTypes(mediaTypeList);

        HttpMessageConverter<?> converter = fastConverter;

        return new HttpMessageConverters(converter);
    }

       public static void main(String[] args) {
        /*
         * 在main方法进行启动我们的应用程序.
         */
        SpringApplication.run(FastJsonApplication.class, args);
    }

}
