package ru.pizza.site.domain.dto.response.basket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductForOrderDTO {
    private int buildingId;
    private String title;
    private int price;
}
