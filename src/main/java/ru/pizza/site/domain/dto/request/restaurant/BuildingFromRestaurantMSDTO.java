package ru.pizza.site.domain.dto.request.restaurant;

import lombok.Data;

import java.util.List;

/**
 * fdsfds
 */
@Data
public class BuildingFromRestaurantMSDTO {
    private int id;
    private String title;
    private List<IngredientFromRestaurantMSDTO> ingredientList;
}
