package com.example.models;

public class Product {
    private String title;
    private String description;
    private Double price;

    public Product(String title, String description, Double price){
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }
    
    
}
