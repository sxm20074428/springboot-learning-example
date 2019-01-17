package com.sxm.springboot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sxm.springboot.property.City;
import com.sxm.springboot.property.PropConfig;
import com.sxm.springboot.property.YmlConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 自定义配置文件测试类
 * <p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertiesTest {

    @Autowired
    private City city;

    @Autowired
    private YmlConfig ymlConfig;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    PropConfig propConfig;

    @Test
    public void getCity() throws JsonProcessingException {

        System.out.println("city: " + objectMapper.writeValueAsString(city));

    }

    @Test
    public void getPropConfig() throws JsonProcessingException {

        System.out.println("propConfig: " + objectMapper.writeValueAsString(propConfig));

    }

    @Test
    public void getYmlConfig() throws JsonProcessingException {
        //测试加载yml文件
        System.out.println("propConfig: " + objectMapper.writeValueAsString(ymlConfig));

    }

}
