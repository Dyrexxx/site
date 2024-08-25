package ru.pizza.site.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.pizza.site.models.enums.Restaurant;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Basket {
    private String fio;
    private String address;
    private List<BasketViewItem> basketViewItemList = new ArrayList<>();


    public static Basket createBasket(Order order) {
        Basket basket = new Basket();
        for (BasketItem basketItem : order.getProducts()) {
            boolean isExists = false;
            for (BasketViewItem basketViewItem : basket.getBasketViewItemList()) {
                if (basketViewItem.getProductName().equals(basketItem.getProductName()) &&
                        basketViewItem.getBuildingId() == basketItem.getBuildingId()) {
                    basketViewItem.addCount();
                    isExists = true;
                    break;
                }
            }
            if (!isExists) {
                basket.getBasketViewItemList().add(new BasketViewItem(basketItem));
            }

        }
        return basket;
    }

    @Getter
    private static class BasketViewItem extends BasketItem {
        private int count;
        private final Restaurant restaurant;

        public BasketViewItem(BasketItem basketItem) {
            super(basketItem.getBuildingId(), basketItem.getProductName(), basketItem.getPrice());
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

        void deductCount() {
            count--;
        }
    }
}
