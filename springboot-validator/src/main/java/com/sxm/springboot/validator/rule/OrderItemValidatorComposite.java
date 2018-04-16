package com.sxm.springboot.validator.rule;

import com.sxm.springboot.domain.OrderItem;
import com.sxm.springboot.validator.model.ErrorNotification;

import java.util.List;

/**
 * collect all errors for a single order item
 *
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/4/10 0010 下午 14:29
 * @since 0.1
 */
public class OrderItemValidatorComposite implements OrderItemValidator {

    private List<OrderItemValidator> validators;

    public OrderItemValidatorComposite(List<OrderItemValidator> orderItemValidators) {
        this.validators = orderItemValidators;
    }

    @Override
    public ErrorNotification validate(OrderItem orderItem) {
        ErrorNotification errorNotification = new ErrorNotification();
        validators.stream()
                .map(validator -> validator.validate(orderItem))
                .forEach(errorNotification::addAll);
        return errorNotification;
    }

    public List<OrderItemValidator> getValidators() {
        return validators;
    }

    public void setValidators(List<OrderItemValidator> validators) {
        this.validators = validators;
    }
}
