package ru.naumen.collection.task2;

public class Empty {

    private String noProducts;

    public Empty(String noProducts) {
        this.noProducts = noProducts;
    }

    @Override
    public String toString() {
        return "Empty{" +
               "noProducts='" + noProducts + '\'' +
               '}';
    }
}