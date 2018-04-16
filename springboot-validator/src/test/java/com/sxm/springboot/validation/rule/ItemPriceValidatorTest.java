package com.sxm.springboot.validation.rule;


import com.sxm.springboot.domain.OrderItem;
import com.sxm.springboot.validation.model.ErrorNotification;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemPriceValidatorTest {

    @Test
    public void validate_priceNull_invalid() {
        ItemPriceValidator validator = new ItemPriceValidator();

        ErrorNotification errorNotification = validator.validate(new OrderItem());

        assertThat(errorNotification.getAllErrors()).isEqualTo(ItemPriceValidator.PRICE_EMPTY_ERROR);
    }

    @Test
    public void validate_priceBlank_invalid() {
        OrderItem orderItem = new OrderItem();
        orderItem.setPrice(" ");
        ItemPriceValidator validator = new ItemPriceValidator();

        ErrorNotification errorNotification = validator.validate(orderItem);

        assertThat(errorNotification.getAllErrors()).isEqualTo(ItemPriceValidator.PRICE_EMPTY_ERROR);
    }

    @Test
    public void validate_priceFormatNotValid_invalid() {
        OrderItem orderItem = new OrderItem();
        String price = "dummy price";
        orderItem.setPrice(price);
        ItemPriceValidator validator = new ItemPriceValidator();

        ErrorNotification errorNotification = validator.validate(orderItem);

        assertThat(errorNotification.getAllErrors()).isEqualTo(
                String.format(ItemPriceValidator.PRICE_INVALID_ERROR_FORMAT, price));
    }

    @Test
    public void validate_priceValid_validated() {
        OrderItem orderItem = new OrderItem();
        orderItem.setPrice("100");
        ItemPriceValidator validator = new ItemPriceValidator();

        ErrorNotification errorNotification = validator.validate(orderItem);

        assertThat(errorNotification.getAllErrors()).isEmpty();
    }
}
