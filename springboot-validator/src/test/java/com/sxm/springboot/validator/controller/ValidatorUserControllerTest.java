package com.sxm.springboot.validator.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidatorUserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    private String requesRightApi(String path) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(path))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    private String requestWrongApi(String path) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.get(path))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    public void test_right_user_param_valid() throws Exception {

        String rightPath = "/user/" + "12?id=123" + "&username=895219077@qq.com" + "&password=123456";
        System.out.println(requesRightApi(rightPath));

    }


//    Body = {"fieldErrors":[{"field":"username","message":"用户名必须是邮箱"},{"field":"id","message":"{user.id.error}"},{"field":"password","message":"may not be empty"}]}
//    Body = {"code":2,"data":null,"msg":"password:may not be empty,id:{user.id.error},username:用户名必须是邮箱,"}

    @Test
    public void test_wrong_user_param_valid() throws Exception {

        String errorPath = "/user/" + "1?username=sxm";
        System.out.println(requestWrongApi(errorPath));

    }

}