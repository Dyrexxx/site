package ru.pizza.site.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketItem {
    private int buildingId;
    private String productName;
    private int price;
}
