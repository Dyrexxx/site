package ru.pizza.site.domain.models;

import lombok.Data;
import ru.pizza.site.domain.dto.response.basket.ProductForOrderDTO;

import java.util.ArrayList;
import java.util.List;

@Data
public class OrderForBasketModel {
    private List<ProductForOrderDTO> productBasketList = new ArrayList<>();

    public void addProduct(ProductForOrderDTO basketItem) {
        this.productBasketList.add(basketItem);
    }
}