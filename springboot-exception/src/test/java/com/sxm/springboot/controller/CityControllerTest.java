package com.sxm.springboot.controller;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CityControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private CityController cityController;

    @Before
    public void setup() {

        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    private JSONObject getCityController(int id) throws Exception {
        String path = "/cities/" + id;
        return new JSONObject(mockMvc.perform(MockMvcRequestBuilders.get(path))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString());
    }

    private MvcResult getView(int id) throws Exception {
        String path = "/cities/" + id;
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(path)).//
                andDo(MockMvcResultHandlers.print())//
                .andReturn();

        return mvcResult;
    }


    @Test
    public void test_exception_handler() throws Exception {
        int i = 1;
        System.out.println(getCityController(i));
        i++;
        assertTrue(1 == getCityController(i).getInt("code"));
        i++;
        assertTrue(getView(i).getModelAndView().getViewName().equals("error/error-business"));
        i++;
        assertTrue(getView(i).getModelAndView().getViewName().equals("error/error"));
    }


}