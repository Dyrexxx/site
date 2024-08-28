package ru.pizza.site.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Restaurant {
    DODO1(1, "Пр. Стоителей 154"),
    DODO2(2, "Ладожская 160"),
    DODO3(3, "Калинина 2"),
    DODO4(4, "Пушкина 128"),
    DODO5(5, "Дворцовая 20");
    private final int id;
    private final String address;
}
