package com.sxm.springboot.validator.controller;

import com.sxm.springboot.domain.Order;
import com.sxm.springboot.domain.OrderItem;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ValidatorOrderControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    private String requesRightApi(String path, String jsonSring) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(path).contentType(MediaType.APPLICATION_JSON).content(jsonSring))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    private String requestWrongApi(String path, String jsonSring) throws Exception {
        return mockMvc.perform(MockMvcRequestBuilders.post(path).contentType(MediaType.APPLICATION_JSON).content(jsonSring))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    @Test
    public void test_right_order_param_valid() throws Exception {

        OrderItem orderItem = createOrderItem();

        Order order = createOrder(orderItem);
        order.setCustomerId("customer id");

        String rightPath = "/order/";

        System.out.println(requesRightApi(rightPath, JacksonHelper.convertObjectToJson(order)));

    }


    @Test
    public void test_wrong_order_param_valid() throws Exception {

        String errorPath = "/order";

        OrderItem orderItem = createOrderItem();
        orderItem.setMenuId(null);
        orderItem.setQuantity(0);
        orderItem.setDescription(null);
        orderItem.setPrice("string");

        Order order = createOrder(orderItem);
        System.out.println(requestWrongApi(errorPath, JacksonHelper.convertObjectToJson(order)));


    }

    private OrderItem createOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setPrice("101");
        orderItem.setDescription("Item description goes here");
        orderItem.setMenuId("menuId");
        orderItem.setQuantity(5);
        return orderItem;
    }

    private Order createOrder(OrderItem orderItem) {
        Order order = new Order();
        order.setCustomerId("customer id");
        order.setOrderItems(Collections.singletonList(orderItem));
        return order;
    }
}