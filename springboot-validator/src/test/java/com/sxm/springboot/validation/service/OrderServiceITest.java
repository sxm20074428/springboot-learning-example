package com.sxm.springboot.validation.service;

import com.sxm.springboot.common.exception.BusinessJSONException;
import com.sxm.springboot.domain.Order;
import com.sxm.springboot.domain.OrderItem;
import com.sxm.springboot.service.order.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceITest {

    @Autowired
    private OrderService orderService;

    @Test
    public void createOrder_orderValid_noError() {
        OrderItem orderItem = createOrderItem();
        Order orderDTO = createOrder(orderItem);

        orderService.createOrder(orderDTO);
    }

    @Test
    public void createOrder_priceInvalid_error() {
        OrderItem orderItem = createOrderItem();
        orderItem.setPrice("string");
        Order order = createOrder(orderItem);

        assertThatExceptionOfType(BusinessJSONException.class).isThrownBy(() -> orderService.createOrder(order));
    }

    @Test
    public void createOrder_descriptionInvalid_error() {
        OrderItem orderItem = createOrderItem();
        orderItem.setDescription(null);
        Order order = createOrder(orderItem);

        assertThatExceptionOfType(BusinessJSONException.class).isThrownBy(() -> orderService.createOrder(order));
    }

    @Test
    public void createOrder_quantityInvalid_error() {
        OrderItem orderItem = createOrderItem();
        orderItem.setQuantity(0);
        Order orderDTO = createOrder(orderItem);

        assertThatExceptionOfType(BusinessJSONException.class).isThrownBy(() -> orderService.createOrder(orderDTO));
    }

    @Test
    public void createOrder_menuInvalid_error() {
        OrderItem orderItem = createOrderItem();
        orderItem.setMenuId(null);
        Order orderDTO = createOrder(orderItem);

        assertThatExceptionOfType(BusinessJSONException.class).isThrownBy(() -> orderService.createOrder(orderDTO));
    }

    private Order createOrder(OrderItem orderItem) {
        Order orderDTO = new Order();
        orderDTO.setCustomerId("customer id");
        orderDTO.setOrderItems(Collections.singletonList(orderItem));
        return orderDTO;
    }

    private OrderItem createOrderItem() {
        OrderItem orderItem = new OrderItem();
        orderItem.setPrice("100");
        orderItem.setDescription("Item description goes here");
        orderItem.setMenuId("menu id");
        orderItem.setQuantity(5);
        return orderItem;
    }
}
