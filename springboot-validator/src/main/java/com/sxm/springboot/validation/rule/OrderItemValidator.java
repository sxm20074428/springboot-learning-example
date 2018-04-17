package com.sxm.springboot.validation.rule;

import com.sxm.springboot.domain.OrderItem;
import com.sxm.springboot.validation.model.ErrorNotification;

public interface OrderItemValidator {

        ErrorNotification validate(OrderItem orderItem);
}
