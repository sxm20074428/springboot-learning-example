package com.sxm.springboot.validation.controller;

import com.sxm.springboot.domain.User;
import com.sxm.springboot.utils.json.JacksonHelper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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
    public void test_get_right_user_param_valid() throws Exception {

        String rightPath = "/user/" + "12?id=123" + "&userName=895219077@qq.com" + "&name=sxm" + "&password=123456";
        System.out.println(requesRightApi(rightPath));

    }

    //    Body = {"fieldErrors":[{"field":"username","message":"用户名必须是邮箱"},{"field":"id","message":"{user.id.error}"},{"field":"password","message":"may not be empty"}]}
    //    Body = {"code":2,"data":null,"msg":"password:may not be empty,id:{user.id.error},username:用户名必须是邮箱,"}

    @Test
    public void test_get_wrong_user_param_valid() throws Exception {

        String errorPath = "/user/" + "1?id=123" + "&userName=sxm";
        System.out.println(requestWrongApi(errorPath));

    }

    @Test
    public void test_post_wrong_user_param_valid() throws Exception {

        String path = "/user/register";
        User user = new User(100, "895219077@qq.com");

        String contentAsString = mockMvc.perform(MockMvcRequestBuilders.post(path).contentType(MediaType.APPLICATION_JSON).content(JacksonHelper.convertObjectToJson(user)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println(contentAsString);
    }

    private User createUser(int id, String userName, String name, String password) {
        User user = new User(id, userName, name, password);
        return user;
    }
}