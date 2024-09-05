package ru.pizza.site.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.pizza.site.dto.request.products.ExpenditureFromProductsMSDTO;
import ru.pizza.site.dto.request.products.ProductFromProductsMSDTO;
import ru.pizza.site.dto.request.restaurant.BuildingFromRestaurantMSDTO;
import ru.pizza.site.dto.request.restaurant.IngredientFromRestaurantMSDTO;
import ru.pizza.site.dto.response.buy_menu.BuildingForBuyMenuDTO;
import ru.pizza.site.dto.response.buy_menu.ProductForBuyMenuDTO;
import ru.pizza.site.mapper.BuildingMapper;
import ru.pizza.site.mapper.ProductMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
@RequiredArgsConstructor
public class SiteService {
    private final RestTemplate restTemplate;
    private final ProductMapper productMapper;
    private final BuildingMapper buildingMapper;

    public List<ProductForBuyMenuDTO> receiveProductsIsProductMS() {
        String url = "http://PRODUCTS/products/view";
        List<ProductFromProductsMSDTO> productListFromProductsMSList =
                List.of(Objects.requireNonNull(restTemplate.getForObject(url, ProductFromProductsMSDTO[].class)));
        System.out.println(productListFromProductsMSList);
        return fillListBuildingCanBuyAndMappingToForBuyMenu(productListFromProductsMSList, receiveBuildingIsRestaurantMS());
    }

    public List<BuildingFromRestaurantMSDTO> receiveBuildingIsRestaurantMS() {
        String url = "http://RESTAURANT/restaurant/api/buildings";
        return List.of(Objects.requireNonNull(restTemplate.getForObject(url, BuildingFromRestaurantMSDTO[].class)));
    }

    /**
     *
     * @param productList
     * @param buildingList
     * @return
     */
    private List<ProductForBuyMenuDTO> fillListBuildingCanBuyAndMappingToForBuyMenu(
            List<ProductFromProductsMSDTO> productList,
            List<BuildingFromRestaurantMSDTO> buildingList) {
        List<ProductForBuyMenuDTO> productForBuyMenuList = new ArrayList<>();
        for (ProductFromProductsMSDTO product : productList) {
            List<BuildingForBuyMenuDTO> buildingForBuyMenuList = new ArrayList<>();
            for (BuildingFromRestaurantMSDTO building : buildingList) {
                boolean isAdequateAmount = isAdequateAmount(product, building);
                BuildingForBuyMenuDTO buildingForBuyMenuDTO = buildingMapper.toBuildingForBuyMenuDTO(building);
                buildingForBuyMenuDTO.setCanBuy(isAdequateAmount);
                buildingForBuyMenuList.add(buildingForBuyMenuDTO);
            }
            ProductForBuyMenuDTO productForBuyMenuDTO = productMapper.toProductForBuyMenu(product);
            productForBuyMenuDTO.setBuildingCanBuyOrNotList(buildingForBuyMenuList);
            productForBuyMenuList.add(productForBuyMenuDTO);
        }
        return productForBuyMenuList;
    }

    private static boolean isAdequateAmount(ProductFromProductsMSDTO product, BuildingFromRestaurantMSDTO building) {
        boolean isAdequateAmount = false;
        for (ExpenditureFromProductsMSDTO expenditure : product.getIngredientEntityList()) {
            for (IngredientFromRestaurantMSDTO ingredient : building.getIngredientList()) {
                if (expenditure.getTitle().equals(ingredient.getTitle())) {
                    if (ingredient.getWeight() > expenditure.getWeight()) {
                        isAdequateAmount = true;
                    }
                    break;
                }
            }
        }
        return isAdequateAmount;
    }
}