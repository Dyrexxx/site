package ru.pizza.site.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Product {
    private String name;
    private String description;
    private String urlImage;
    private int price;
    private String type;
    private List<Building> buildingList = new ArrayList<>();
}
