package com.sxm.springboot;

import com.sxm.springboot.web.HelloWorldController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/7/26 12:38
 * @since 0.1
 */

// spring boot 1.4.0 版本之后使用以下两个配置
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldControllerTest {

    @Autowired
    private WebApplicationContext context;

    //mock api 模拟http请求
    private MockMvc mockMvc;

    @Before
    public void setUp() {

        //独立安装测试
//        mockMvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();

        //集成Web环境测试（此种方式并不会集成真正的web环境，而是通过相应的Mock API进行模拟测试，无须启动服务器）
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

    }

    @Test
    public void index() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/")//
                .accept(MediaType.APPLICATION_JSON))//
                .andExpect(status().isOk())//
                .andExpect(content().contentType("application/json;charset=UTF-8"))//
                .andDo(print());

    }

}
