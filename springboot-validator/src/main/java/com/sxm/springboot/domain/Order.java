package com.sxm.springboot.domain;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

public class Order implements Serializable {

    @NotNull
    private String customerId;

    private List<OrderItem> orderItems;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customerId='" + customerId + '\'' +
                ", orderItems=" + orderItems +
                '}';
    }
}
