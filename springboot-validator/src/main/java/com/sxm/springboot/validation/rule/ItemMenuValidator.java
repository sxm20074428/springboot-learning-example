package com.sxm.springboot.validation.rule;


import com.sxm.springboot.dao.MenuRepository;
import com.sxm.springboot.domain.OrderItem;
import com.sxm.springboot.validation.model.ErrorNotification;

import java.util.Optional;
import java.util.function.Consumer;

public class ItemMenuValidator implements OrderItemValidator {

    private MenuRepository menuRepository;

    static final String MISSING_MENU_ERROR = "A menu item must be specified.";
    static final String INVALID_MENU_ERROR_FORMAT = "Given menu [%s] does not exist.";

    public ItemMenuValidator(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public ErrorNotification validate(OrderItem orderItem) {
        ErrorNotification errorNotification = new ErrorNotification();
        Optional.ofNullable(orderItem.getMenuId())
                .map(String::trim)
                .filter(menuId -> !menuId.isEmpty())
                .ifPresentOrElse(
                        validateMenuExists(errorNotification),
                        () -> errorNotification.addError(MISSING_MENU_ERROR)
                );
        return errorNotification;
    }

    private Consumer<String> validateMenuExists(ErrorNotification errorNotification) {
        return menuId -> {
            if (!menuRepository.menuExists(menuId)) {
                errorNotification.addError(String.format(INVALID_MENU_ERROR_FORMAT, menuId));
            }
        };
    }

    public MenuRepository getMenuRepository() {
        return menuRepository;
    }

    public void setMenuRepository(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

}
