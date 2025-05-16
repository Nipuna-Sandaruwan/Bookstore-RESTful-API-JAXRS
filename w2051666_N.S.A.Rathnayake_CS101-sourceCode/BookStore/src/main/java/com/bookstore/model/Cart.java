package com.bookstore.model;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private String id;
    private String customerId;
    private List<CartItem> items = new ArrayList<>();

    // Constructors, getters, and setters
    public Cart() {}

    public Cart(String id, String customerId) {
        this.id = id;
        this.customerId = customerId;
    }

    // Getters and setters for all fields
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public List<CartItem> getItems() { return items; }
    public void setItems(List<CartItem> items) { this.items = items; }
}