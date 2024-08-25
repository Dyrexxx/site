package ru.pizza.site.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.pizza.site.models.BasketItem;
import ru.pizza.site.models.Building;
import ru.pizza.site.models.Product;
import ru.pizza.site.models.enums.Restaurant;
import ru.pizza.site.services.SiteService;

import java.util.List;

@Controller
@SessionAttributes({"modelBuilding", "productsList"})
@RequestMapping("/site")
public class SiteController {
    private final SiteService siteService;
    private Building modelBuilding;

    @Autowired
    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @ModelAttribute("productsList")
    public List<Product> productList() {
        return siteService.getProducts();
    }

    @ModelAttribute("basketItem")
    public BasketItem basketItem() {
        return new BasketItem();
    }

    @ModelAttribute("modelBuilding")
    public Building emptyBuilding() {
        return modelBuilding = new Building();
    }

    @PostMapping("/choose-select")
    public String chooseSelect(@ModelAttribute Building buildingId) {
        modelBuilding.setId(buildingId.getId());
        return "redirect:/site";
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("restaurantEnum", Restaurant.values());
        return "index";
    }
}