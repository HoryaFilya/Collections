package ru.naumen.collection.task2;

public class FoodAndDrinks {

    private String food;
    private String drink;

    public FoodAndDrinks(String food, String drink) {
        this.food = food;
        this.drink = drink;
    }

    @Override
    public String toString() {
        return "FoodAndDrinks{" +
               "food='" + food + '\'' +
               ", drink='" + drink + '\'' +
               '}';
    }
}