package com.Shoppingcart.cartapp.model;

public class Product {
    private final int id;
    private String name;
    private double price;
    private String imageUrl;

    public Product (int id, String name, double price, String imageUrl){
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public double getPrice(){
        return price;
    }
    public String getImageUrl(){
        return imageUrl;
    }

    //Setters to allow the chages furthe 
    public void setName(String name){
        this.name = name;
    }

    public void setPrice(double price){
        this.price = price;
    }
    
    public void setImageUrl(String imageUrl){
        this.imageUrl = imageUrl;
    }


}
