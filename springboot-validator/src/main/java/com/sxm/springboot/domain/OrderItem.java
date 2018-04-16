package com.sxm.springboot.domain;

/**
 * @author 苏晓蒙
 * @version 0.1
 * @time 2018/4/9 0009 上午 11:25
 * @since 0.1
 */
public class OrderItem {

    private String menuId;
    private String description;
    private String price;
    private Integer quantity;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "menuId='" + menuId + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
