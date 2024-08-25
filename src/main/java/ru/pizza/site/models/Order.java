package ru.pizza.site.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private List<BasketItem> products = new ArrayList<>();

    public void addProduct(BasketItem basketItem) {
        this.products.add(basketItem);
    }
}