package ru.pizza.site.domain.dto.request.products;

import lombok.Data;
import ru.pizza.site.domain.dto.response.buy_menu.ProductForBuyMenuDTO;

import java.util.List;

@Data
public class ProductFromProductsMSDTO extends ProductForBuyMenuDTO {
    private String title;
    private String description;
    private String urlImage;
    private int price;
    private String type;
    private List<ExpenditureFromProductsMSDTO> ingredientEntityList;
}
