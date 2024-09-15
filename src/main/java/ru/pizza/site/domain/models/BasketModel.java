package ru.pizza.site.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.pizza.site.domain.dto.response.basket.ProductForOrderDTO;
import ru.pizza.site.domain.enums.Restaurant;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasketModel {
    private String fio;
    private String address;
    private List<ProductInBasketDTO> productsList = new ArrayList<>();


    public static BasketModel createBasketOrAddProduct(OrderForBasketModel order) {
        BasketModel basketModel = new BasketModel();
        for (ProductForOrderDTO basketItem : order.getProductBasketList()) {
            boolean isExists = false;
            for (ProductInBasketDTO basketViewItem : basketModel.getProductsList()) {
                if (basketViewItem.getTitle().equals(basketItem.getTitle()) &&
                        basketViewItem.getBuildingId() == basketItem.getBuildingId()) {
                    basketViewItem.addCount();
                    isExists = true;
                    break;
                }
            }
            if (!isExists) {
                basketModel.getProductsList().add(new ProductInBasketDTO(basketItem));
            }

        }
        return basketModel;
    }

    @Getter
    private static class ProductInBasketDTO extends ProductForOrderDTO {
        private int count;
        private final Restaurant restaurant;

        public ProductInBasketDTO(ProductForOrderDTO basketItem) {
            super(basketItem.getBuildingId(), basketItem.getTitle(), basketItem.getPrice());
            count = 1;
            restaurant = findRestaurant(getBuildingId());
        }

        private Restaurant findRestaurant(int id) {
            for (Restaurant r : Restaurant.values()) {
                if (r.getId() == id) {
                    return Restaurant.valueOf(r.name());
                }
            }
            return null;
        }

        @Override
        public int getPrice() {
            return super.getPrice() * count;
        }

        void addCount() {
            count++;
        }
    }
}