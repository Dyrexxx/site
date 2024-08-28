package ru.pizza.site.dto.response.basket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductForOrder {
    private int buildingId;
    private String title;
    private int price;
}
