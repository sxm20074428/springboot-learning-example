package com.sxm.springboot.controller;

import com.sxm.springboot.common.dto.ResultResponse;
import com.sxm.springboot.domain.Order;
import com.sxm.springboot.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResultResponse order(@Valid @RequestBody Order order) {
        orderService.createOrder(order);
        return ResultResponse.createSuccessResponse(order);
    }
}
