package com.sxm.springboot.validation.rule;

import com.sxm.springboot.domain.OrderItem;
import com.sxm.springboot.validation.model.ErrorNotification;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemDescriptionValidatorTest {

    @Test
    public void validate_descriptionIsNull_invalid() {

        ItemDescriptionValidator validator = new ItemDescriptionValidator();

        ErrorNotification errorNotification = validator.validate(new OrderItem());

        assertThat(errorNotification.getAllErrors()).isEqualTo(ItemDescriptionValidator.MISSING_ITEM_DESCRIPTION);
    }

    @Test
    public void validate_descriptionIsBlank_invalid() {
        OrderItem orderItem = new OrderItem();
        orderItem.setDescription("     ");

        ItemDescriptionValidator validator = new ItemDescriptionValidator();
        ErrorNotification errorNotification = validator.validate(orderItem);

        assertThat(errorNotification.getAllErrors()).isEqualTo(ItemDescriptionValidator.MISSING_ITEM_DESCRIPTION);
    }

    @Test
    public void validate_descriptionGiven_valid() {
        OrderItem orderItem = new OrderItem();
        orderItem.setDescription("dummy description");

        ItemDescriptionValidator validator = new ItemDescriptionValidator();
        ErrorNotification errorNotification = validator.validate(orderItem);

        assertThat(errorNotification.getAllErrors()).isEmpty();
    }
}
