package ru.naumen.collection.task2;

public class Drink {

    private String drink;

    public Drink(String drink) {
        this.drink = drink;
    }

    @Override
    public String toString() {
        return "Drink{" +
               "drink='" + drink + '\'' +
               '}';
    }
}