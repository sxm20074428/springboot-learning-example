package com.sxm.springboot;

import com.sxm.springboot.property.City;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesTest.class);

    @Autowired
    private City city;

    @Test
    public void getHomeProperties() {
        LOGGER.info("\n\n" + city.toString() + "\n");
    }

}
