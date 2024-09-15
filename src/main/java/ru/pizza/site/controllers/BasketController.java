package ru.pizza.site.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.pizza.site.domain.models.BasketModel;
import ru.pizza.site.domain.models.OrderForBasketModel;
import ru.pizza.site.domain.dto.response.basket.ProductForOrderDTO;

@Controller
@RequestMapping("/site/basket")
@RequiredArgsConstructor
@SessionAttributes({"productOrder"})
public class BasketController {
    private final RestTemplate restTemplate;

    @ModelAttribute("basket")
    public BasketModel basket(@ModelAttribute("productOrder") OrderForBasketModel order) {
        return BasketModel.createBasketOrAddProduct(order);
    }


    @GetMapping
    public String index() {
        return "basket";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("basketItem") ProductForOrderDTO basketItem, @ModelAttribute("productOrder") OrderForBasketModel order) {
        order.addProduct(basketItem);
        return "redirect:/site";
    }

    @PostMapping("/pay")
    public String pay(@ModelAttribute("basket") BasketModel basketModel) {
        restTemplate.postForEntity("http://RESTAURANT/restaurant/api/orders", basketModel, BasketModel.class);
        return "redirect:/site";
    }
}