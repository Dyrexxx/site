package ru.pizza.site.dto.response.basket;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.pizza.site.enums.Restaurant;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Basket {
    private String fio;
    private String address;
    private List<ProductInBasket> productsList = new ArrayList<>();


    public static Basket createBasketOrAddProduct(OrderForBasket order) {
        Basket basket = new Basket();
        for (ProductForOrder basketItem : order.getProductBasketList()) {
            boolean isExists = false;
            for (ProductInBasket basketViewItem : basket.getProductsList()) {
                if (basketViewItem.getTitle().equals(basketItem.getTitle()) &&
                        basketViewItem.getBuildingId() == basketItem.getBuildingId()) {
                    basketViewItem.addCount();
                    isExists = true;
                    break;
                }
            }
            if (!isExists) {
                basket.getProductsList().add(new ProductInBasket(basketItem));
            }

        }
        return basket;
    }

    @Getter
    private static class ProductInBasket extends ProductForOrder {
        private int count;
        private final Restaurant restaurant;

        public ProductInBasket(ProductForOrder basketItem) {
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