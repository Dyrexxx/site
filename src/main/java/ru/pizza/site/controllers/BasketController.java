package ru.pizza.site.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.pizza.site.dto.response.basket.Basket;
import ru.pizza.site.dto.response.basket.OrderForBasket;
import ru.pizza.site.dto.response.basket.ProductForOrder;

@Controller
@RequestMapping("/site/basket")
@RequiredArgsConstructor
@SessionAttributes({"productOrder"})
public class BasketController {
    private final RestTemplate restTemplate;

    @ModelAttribute("basket")
    public Basket basket(@ModelAttribute("productOrder") OrderForBasket order) {
        return Basket.createBasketOrAddProduct(order);
    }


    @GetMapping
    public String index() {
        return "basket";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("basketItem") ProductForOrder basketItem, @ModelAttribute("productOrder") OrderForBasket order) {
        order.addProduct(basketItem);
        return "redirect:/site";
    }

    @PostMapping("/pay")
    @ResponseBody
    public ResponseEntity<Basket> pay(@ModelAttribute("basket") Basket basket) {
        return restTemplate.postForEntity("http://localhost:8085/dodo/newOrder", basket, Basket.class);
    }
}