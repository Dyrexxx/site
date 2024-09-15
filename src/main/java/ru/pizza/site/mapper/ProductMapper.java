package ru.pizza.site.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.pizza.site.domain.dto.request.products.ProductFromProductsMSDTO;
import ru.pizza.site.domain.dto.response.buy_menu.ProductForBuyMenuDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    ProductForBuyMenuDTO toProductForBuyMenu(ProductFromProductsMSDTO productsFromProductsMSDTO);
}
