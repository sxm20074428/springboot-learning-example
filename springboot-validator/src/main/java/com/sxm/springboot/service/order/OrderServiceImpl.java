package com.sxm.springboot.service.order;

import com.sxm.springboot.common.enums.ErrorCodeEnum;
import com.sxm.springboot.common.enums.ModuleCodeEnum;
import com.sxm.springboot.common.exception.BusinessJSONException;
import com.sxm.springboot.domain.Order;
import com.sxm.springboot.validation.model.ErrorNotification;
import com.sxm.springboot.validation.rule.OrderItemValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderItemValidator orderItemValidator;

    public void createOrder(Order order) {
        ErrorNotification errorNotification = new ErrorNotification();
        order.getOrderItems()
                .stream()
                .map(orderItemValidator::validate)
                .forEach(errorNotification::addAll);

        if (errorNotification.hasError()) {
            throw new BusinessJSONException(ModuleCodeEnum.BASIC_DATA_Module, ErrorCodeEnum.INNER_ERROR, errorNotification.getAllErrors());
        }

        LOGGER.info("Order {} saved", order);
    }
}
