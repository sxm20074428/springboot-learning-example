package com.sxm.springboot.validator.rule;

import com.sxm.springboot.domain.OrderItem;
import com.sxm.springboot.validator.model.ErrorNotification;

public interface OrderItemValidator {

        ErrorNotification validate(OrderItem orderItem);
}
