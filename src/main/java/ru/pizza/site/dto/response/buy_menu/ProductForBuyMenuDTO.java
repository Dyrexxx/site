package ru.pizza.site.dto.response.buy_menu;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductForBuyMenuDTO {
    private String title;
    private String description;
    private String urlImage;
    private int price;
    private String type;
    private List<BuildingForBuyMenuDTO> buildingCanBuyOrNotList = new ArrayList<>();
}
