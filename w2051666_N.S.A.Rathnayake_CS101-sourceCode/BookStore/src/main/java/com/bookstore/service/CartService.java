package com.bookstore.service;

import com.bookstore.exception.BookNotFoundException;
import com.bookstore.exception.CartNotFoundException;
import com.bookstore.exception.CustomerNotFoundException;
import com.bookstore.exception.OutOfStockException;
import com.bookstore.model.Cart;
import com.bookstore.model.CartItem;
import com.bookstore.service.BookService;
import com.bookstore.service.CustomerService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartService {
    private static Map<String, Cart> carts = new HashMap<>();
    private BookService bookService = new BookService();
    private CustomerService customerService = new CustomerService();

    public Cart getCart(String customerId) {
        // Check if customer exists
        customerService.getCustomer(customerId);
        
        Cart cart = carts.get(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart for customer " + customerId + " not found");
        }
        return cart;
    }

    public Cart addItemToCart(String customerId, CartItem item) {
        // Check if customer exists
        customerService.getCustomer(customerId);
        
        // Check if book exists and has sufficient stock
        bookService.getBook(item.getBookId());
        
        Cart cart = carts.get(customerId);
        if (cart == null) {
            cart = new Cart("cart-" + customerId, customerId);
            carts.put(customerId, cart);
        }
        
        // Check if item already exists in cart
        boolean itemExists = false;
        for (CartItem cartItem : cart.getItems()) {
            if (cartItem.getBookId().equals(item.getBookId())) {
                cartItem.setQuantity(cartItem.getQuantity() + item.getQuantity());
                itemExists = true;
                break;
            }
        }
        
        if (!itemExists) {
            cart.getItems().add(item);
        }
        
        return cart;
    }

    public Cart updateCartItem(String customerId, String bookId, int quantity) {
        // Check if customer exists
        customerService.getCustomer(customerId);
        
        // Check if book exists
        bookService.getBook(bookId);
        
        Cart cart = carts.get(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart for customer " + customerId + " not found");
        }
        
        for (CartItem item : cart.getItems()) {
            if (item.getBookId().equals(bookId)) {
                item.setQuantity(quantity);
                return cart;
            }
        }
        
        throw new BookNotFoundException("Book with id " + bookId + " not found in cart");
    }

    public void removeItemFromCart(String customerId, String bookId) {
        // Check if customer exists
        customerService.getCustomer(customerId);
        
        Cart cart = carts.get(customerId);
        if (cart == null) {
            throw new CartNotFoundException("Cart for customer " + customerId + " not found");
        }
        
        cart.getItems().removeIf(item -> item.getBookId().equals(bookId));
    }
}