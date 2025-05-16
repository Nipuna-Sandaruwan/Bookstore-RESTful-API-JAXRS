package com.bookstore.model;

public class CartItem {
    private String bookId;
    private int quantity;

    // Constructors, getters, and setters
    public CartItem() {}

    public CartItem(String bookId, int quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }

    // Getters and setters for all fields
    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { this.bookId = bookId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}