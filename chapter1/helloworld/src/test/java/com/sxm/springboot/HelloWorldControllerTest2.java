package com.sxm.springboot;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2017/7/26 12:38
 * @since 0.1
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HelloWorldControllerTest2 {

    //mock api 模拟http请求
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getHello() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/")//
                .accept(MediaType.APPLICATION_JSON))//
                .andExpect(status().isOk())//
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(content().string(equalTo("Greetings from Spring Boot!")));

    }

}
