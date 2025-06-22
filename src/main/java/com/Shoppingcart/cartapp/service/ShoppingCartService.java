package com.Shoppingcart.cartapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.Shoppingcart.cartapp.model.Product;

@Service
public class ShoppingCartService {

    private final List<Product> cartItems = new ArrayList<>();

    public List<Product> getAllList() {
        List<Product> products = new ArrayList<>(); //Demo data for the shopping cart
        // In a real application, this data would typically come from a database or an external API.

        products.add(new Product(1, "Laptop", 80000.00, "/images/Leptop.jpg"));
        products.add(new Product(2, "Smartphone", 50000.00, "/images/Smartphone.jpg"));
        products.add(new Product(3, "Headphones", 1500.00, "/images/headphone.jpg"));
        products.add(new Product(4, "Smartwatch", 2000.00, "/images/smartwatch.jpg"));

        return products;
    }

    public void addToCart(Product product) {
        cartItems.add(product);
    }

    public List<Product> getCartItems() {
        return cartItems;
    }

    public double getPrice() {
        double total = 0.0;
        for (Product products : cartItems) {
            total += products.getPrice();
        }
        return total;
    }

    public void RemoveFromCart(int id) {
        for (Product p : cartItems) {
            if (p.getId() == id) {
                cartItems.remove(p);
                break;
            }
        }
    }

    private List<Product> savedItems = new ArrayList<>();

    public void saveForLater(int id) {
        for (Product p : cartItems) {
            if (p.getId() == id) {
                cartItems.remove(p);
                savedItems.add(p);
                break;
            }
        }
    }

    public void moveToCart(int id) {
        for (Product p : savedItems) {
            if (p.getId() == id) {
                savedItems.remove(p);
                cartItems.add(p);
                break;
            }
        }
    }

    public void removeFromSaved(int id) {
        for (Product p : savedItems) {
            if (p.getId() == id) {
                savedItems.remove(p);
                break;
            }
        }
    }

    public List<Product> getSavedItems() {
        return savedItems;
    }

}
