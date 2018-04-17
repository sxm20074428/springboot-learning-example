package com.sxm.springboot.validation.rule;


import com.sxm.springboot.domain.OrderItem;
import com.sxm.springboot.validation.model.ErrorNotification;

import java.util.Optional;
import java.util.function.Consumer;

public class ItemQuantityValidator implements OrderItemValidator {

    static final String MISSING_QUANTITY_ERROR = "Quantity must be given";
    static final String INVALID_QUANTITY_ERROR = "Given quantity %s is not valid.";

    @Override
    public ErrorNotification validate(OrderItem orderItem) {
        ErrorNotification errorNotification = new ErrorNotification();
        Optional.ofNullable(orderItem)
                .map(OrderItem::getQuantity)
                .ifPresentOrElse(
                        validateQuantity(errorNotification),
                        () -> errorNotification.addError(MISSING_QUANTITY_ERROR)
                );
        return errorNotification;
    }

    private Consumer<? super Integer> validateQuantity(ErrorNotification errorNotification) {
        return quantity -> {
            if (quantity <= 0) {
                errorNotification.addError(String.format(INVALID_QUANTITY_ERROR, quantity));
            }
        };
    }
}
