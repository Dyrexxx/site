package ru.pizza.site.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.pizza.site.domain.dto.request.restaurant.BuildingFromRestaurantMSDTO;
import ru.pizza.site.domain.dto.response.buy_menu.BuildingForBuyMenuDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BuildingMapper {
    BuildingForBuyMenuDTO toBuildingForBuyMenuDTO(BuildingFromRestaurantMSDTO buildingFromRestaurantMSDTO);
}
