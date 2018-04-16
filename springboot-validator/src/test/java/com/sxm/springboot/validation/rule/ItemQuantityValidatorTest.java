package com.sxm.springboot.validation.rule;


import com.sxm.springboot.domain.OrderItem;
import com.sxm.springboot.validation.model.ErrorNotification;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemQuantityValidatorTest {

    @Test
    public void validate_quantityIsNull_invalid() {

        ItemQuantityValidator validator = new ItemQuantityValidator();

        ErrorNotification errorNotification = validator.validate(new OrderItem());

        assertThat(errorNotification.getAllErrors()).isEqualTo(ItemQuantityValidator.MISSING_QUANTITY_ERROR);
    }

    @Test
    public void validate_quantityIsZero_invalid() {
        OrderItem orderItem = new OrderItem();
        int quantity = 0;
        orderItem.setQuantity(quantity);
        ItemQuantityValidator validator = new ItemQuantityValidator();

        ErrorNotification errorNotification = validator.validate(orderItem);

        assertThat(errorNotification.getAllErrors()).isEqualTo(String.format(ItemQuantityValidator.INVALID_QUANTITY_ERROR, quantity));
    }

    @Test
    public void validate_quantityNegative_invalid() {
        OrderItem orderItem = new OrderItem();
        int quantity = -1;
        orderItem.setQuantity(quantity);
        ItemQuantityValidator validator = new ItemQuantityValidator();

        ErrorNotification errorNotification = validator.validate(orderItem);

        assertThat(errorNotification.getAllErrors()).isEqualTo(String.format(ItemQuantityValidator.INVALID_QUANTITY_ERROR, quantity));
    }

    @Test
    public void validate_quantityValid_validated() {
        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(5);
        ItemQuantityValidator validator = new ItemQuantityValidator();

        ErrorNotification errorNotification = validator.validate(orderItem);

        assertThat(errorNotification.getAllErrors()).isEmpty();
    }
}
