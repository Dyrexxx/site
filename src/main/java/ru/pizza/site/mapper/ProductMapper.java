package ru.pizza.site.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.pizza.site.dto.request.products.ProductFromProductsMSDTO;
import ru.pizza.site.dto.response.buy_menu.ProductForBuyMenuDTO;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProductMapper {
    ProductForBuyMenuDTO toProductForBuyMenu(ProductFromProductsMSDTO productsFromProductsMSDTO);
    List<ProductForBuyMenuDTO> toProductForBuyMenuList(List<ProductFromProductsMSDTO> productFromProductsMSDTOList);
}
