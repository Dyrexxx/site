package ru.pizza.site.models;

import lombok.Data;

@Data
public class Building {
    private int id;
    private String name;
    boolean isPresent;
}
