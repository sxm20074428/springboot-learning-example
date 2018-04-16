package com.sxm.springboot.validator.rule;


import com.sxm.springboot.domain.OrderItem;
import com.sxm.springboot.validator.model.ErrorNotification;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Consumer;

public class ItemPriceValidator implements OrderItemValidator {

    static final String PRICE_EMPTY_ERROR = "Price cannot be empty.";
    static final String PRICE_INVALID_ERROR_FORMAT = "Given price [%s] is not in valid format";

    @Override
    public ErrorNotification validate(OrderItem orderItem) {
        ErrorNotification errorNotification = new ErrorNotification();
        Optional.ofNullable(orderItem)
                .map(OrderItem::getPrice)
                .map(String::trim)
                .filter(itemPrice -> !itemPrice.isEmpty())
                .ifPresentOrElse(
                        validatePriceFormat(errorNotification),
                        () -> errorNotification.addError(PRICE_EMPTY_ERROR)
                );
        return errorNotification;
    }

    private Consumer<? super String> validatePriceFormat(ErrorNotification errorNotification) {
        return itemPrice -> {
            try {
                new BigDecimal(itemPrice);
            } catch (NumberFormatException ex) {
                errorNotification.addError(String.format(PRICE_INVALID_ERROR_FORMAT, itemPrice));
            }
        };
    }
}
