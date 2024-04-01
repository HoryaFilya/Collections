package ru.naumen.collection.task2;

import ru.naumen.collection.task2.exception.NoTicketFoundException;

import java.util.HashMap;
import java.util.Map;

public class Products {

    private Empty noProducts;
    private Drink drink;
    private FoodAndDrinks foodAndDrinks;
    private Map<Long, Products> products = new HashMap<>();

    public Products() {
    }

    public Products(Empty noProducts, Drink drink, FoodAndDrinks foodAndDrinks) {
        this.noProducts = noProducts;
        this.drink = drink;
        this.foodAndDrinks = foodAndDrinks;
    }

    @Override
    public String toString() {
        return "Products{" +
               "noProducts=" + noProducts +
               ", drink=" + drink +
               ", foodAndDrinks=" + foodAndDrinks +
               '}';
    }

    public Products getProducts(Ticket ticket) {
        if (ticket == null) {
            throw new NoTicketFoundException("Необходимо предъявить билет!");
        }

        return products.get(ticket.getId());
    }

    public void buy(Ticket ticket, Empty noProducts, Drink drink, FoodAndDrinks foodAndDrinks) {
        if (ticket == null) {
            throw new NoTicketFoundException("Необходимо предъявить билет!");
        }

        products.put(ticket.getId(), new Products(noProducts, drink, foodAndDrinks));
    }
}