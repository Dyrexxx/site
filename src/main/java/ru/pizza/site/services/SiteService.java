package ru.pizza.site.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.pizza.site.models.Product;

import java.util.List;


@Service
public class SiteService {
    private final RestTemplate restTemplate;

    @Autowired
    public SiteService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Product> getProducts() {
        String url = "http://localhost:8082/products/view";
        Product[] products = restTemplate.getForObject(url, Product[].class);
        return List.of(products);
    }
}