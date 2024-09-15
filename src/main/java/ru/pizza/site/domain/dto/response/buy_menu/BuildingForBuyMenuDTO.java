package ru.pizza.site.domain.dto.response.buy_menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingForBuyMenuDTO {
    private int id;
    private String title;
    private boolean canBuy;
}
