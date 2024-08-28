package ru.pizza.site.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pizza.site.dto.request.restaurant.BuildingFromRestaurantMSDTO;
import ru.pizza.site.dto.response.basket.ProductForOrder;
import ru.pizza.site.dto.response.buy_menu.ProductForBuyMenuDTO;
import ru.pizza.site.enums.Restaurant;
import ru.pizza.site.services.SiteService;

import java.util.List;

@Controller
@SessionAttributes({"emptyBuilding", "productsAndExpenditureList"})
@RequiredArgsConstructor
@RequestMapping("/site")
public class SiteController {
    private final SiteService siteService;

    @ModelAttribute("productsAndExpenditureList")
    public List<ProductForBuyMenuDTO> productsAndExpenditureListModel() {
        return siteService.receiveProductsIsProductMS();
    }

    @ModelAttribute("basketItem")
    public ProductForOrder basketItem() {
        return new ProductForOrder();
    }

    @ModelAttribute("emptyBuilding")
    public BuildingFromRestaurantMSDTO emptyBuilding() {
        return new BuildingFromRestaurantMSDTO();
    }

    @PostMapping("/choose-select")
    public String chooseSelect(@ModelAttribute BuildingFromRestaurantMSDTO buildingId,
                               @ModelAttribute("emptyBuilding") BuildingFromRestaurantMSDTO emptyBuilding) {
        emptyBuilding.setId(buildingId.getId());
        return "redirect:/site";
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("restaurantEnum", Restaurant.values());
        return "index";
    }
}