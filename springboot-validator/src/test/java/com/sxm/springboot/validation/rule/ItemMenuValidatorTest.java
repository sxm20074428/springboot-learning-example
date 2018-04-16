package com.sxm.springboot.validation.rule;


import com.sxm.springboot.dao.MenuRepository;
import com.sxm.springboot.domain.OrderItem;
import com.sxm.springboot.validation.model.ErrorNotification;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ItemMenuValidatorTest {

    @Test
    public void validate_menuIdInvalid_invalid() {
        OrderItem orderItem = new OrderItem();
        String menuId = "some menu id";
        orderItem.setMenuId(menuId);
        MenuRepository menuRepository = mock(MenuRepository.class);
        when(menuRepository.menuExists(any())).thenReturn(false);

        ItemMenuValidator validator = new ItemMenuValidator(menuRepository);

        ErrorNotification errorNotification = validator.validate(orderItem);

        assertThat(errorNotification.getAllErrors()).isEqualTo(String.format(ItemMenuValidator.INVALID_MENU_ERROR_FORMAT, menuId));
    }

    @Test
    public void validate_menuIdNull_invalid() {
        MenuRepository menuRepository = mock(MenuRepository.class);
        when(menuRepository.menuExists(any())).thenReturn(true);
        ItemMenuValidator validator = new ItemMenuValidator(menuRepository);

        ErrorNotification errorNotification = validator.validate(new OrderItem());

        assertThat(errorNotification.getAllErrors()).isEqualTo(ItemMenuValidator.MISSING_MENU_ERROR);
    }

    @Test
    public void validate_menuIdIsBlank_invalid() {
        OrderItem orderItem = new OrderItem();
        orderItem.setMenuId("       \t");
        MenuRepository menuRepository = mock(MenuRepository.class);
        when(menuRepository.menuExists(any())).thenReturn(true);
        ItemMenuValidator validator = new ItemMenuValidator(menuRepository);

        ErrorNotification errorNotification = validator.validate(orderItem);

        assertThat(errorNotification.getAllErrors()).isEqualTo(ItemMenuValidator.MISSING_MENU_ERROR);
    }

    @Test
    public void validate_menuIdValid_validated() {
        OrderItem orderItem = new OrderItem();
        String menuId = "some menu id";
        orderItem.setMenuId(menuId);
        MenuRepository menuRepository = mock(MenuRepository.class);
        when(menuRepository.menuExists(menuId)).thenReturn(true);
        ItemMenuValidator validator = new ItemMenuValidator(menuRepository);

        ErrorNotification errorNotification = validator.validate(orderItem);

        assertThat(errorNotification.getAllErrors()).isEmpty();
    }
}
