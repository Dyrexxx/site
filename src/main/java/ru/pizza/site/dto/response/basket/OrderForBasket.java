package ru.pizza.site.dto.response.basket;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderForBasket {
    private List<ProductForOrder> productBasketList = new ArrayList<>();

    public void addProduct(ProductForOrder basketItem) {
        this.productBasketList.add(basketItem);
    }
}