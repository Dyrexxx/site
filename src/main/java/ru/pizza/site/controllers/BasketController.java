package ru.pizza.site.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.pizza.site.models.Basket;
import ru.pizza.site.models.BasketItem;
import ru.pizza.site.models.Order;

import java.util.List;

@Controller
@RequestMapping("/site/basket")
@SessionAttributes({"productOrder"})
public class BasketController {
    private final RestTemplate restTemplate;

    @Autowired
    public BasketController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @ModelAttribute(name = "productOrder")
    public Order order() {
        return new Order();
    }

    @ModelAttribute("basket")
    public Basket basket(@ModelAttribute("productOrder") Order order) {
        return Basket.createBasket(order);
    }


    @GetMapping
    public String index() {
        return "basket";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute BasketItem basketItem, @ModelAttribute("productOrder") Order order) {
        order.addProduct(basketItem);
        return "redirect:/site";
    }

    @PostMapping("/pay")
    @ResponseBody
    public ResponseEntity pay(@ModelAttribute("basket") Basket basket) {
        restTemplate.exchange("http://localhost:8082/products/order",
                HttpMethod.POST, new HttpEntity<>(basket), Basket.class);
        return ResponseEntity.ok(null);
    }
}