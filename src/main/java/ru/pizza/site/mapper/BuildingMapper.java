package ru.pizza.site.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import ru.pizza.site.dto.request.restaurant.BuildingFromRestaurantMSDTO;
import ru.pizza.site.dto.response.buy_menu.BuildingForBuyMenuDTO;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BuildingMapper {
    BuildingForBuyMenuDTO toBuildingForBuyMenuDTO(BuildingFromRestaurantMSDTO buildingFromRestaurantMSDTO);
    List<BuildingForBuyMenuDTO> toBuildingCanBuyOrNotForBuyMenu(
            List<BuildingFromRestaurantMSDTO> buildingFromRestaurantMSDTOS);
}
