package com.sxm.springboot.validation.rule;

import com.sxm.springboot.domain.OrderItem;
import com.sxm.springboot.validation.model.ErrorNotification;

import java.util.Optional;

public class ItemDescriptionValidator implements OrderItemValidator {

    static final String MISSING_ITEM_DESCRIPTION = "Item description should be provided";

    /**
     * I am a bit uncomfortable with the use of the ifPresentOrElse method above.
     * The main reason I am using it here is because Optionals don’t have something like an ifNotPresent method,
     * which would have allowed me to take an action only when the value is not present
     *
     * @param
     * @return
     * @author 苏晓蒙
     * @time 2018/4/12 0012 上午 9:41
     * @version 0.1
     * @since 0.1
     */
    @Override
    public ErrorNotification validate(OrderItem orderItem) {
        ErrorNotification errorNotification = new ErrorNotification();
        Optional.ofNullable(orderItem)
                .map(OrderItem::getDescription)
                .map(String::trim)
                .filter(description -> !description.isEmpty())//筛选不为空
                .ifPresentOrElse(description -> {
                }, () -> errorNotification.addError(MISSING_ITEM_DESCRIPTION));

        return errorNotification;
    }
}
