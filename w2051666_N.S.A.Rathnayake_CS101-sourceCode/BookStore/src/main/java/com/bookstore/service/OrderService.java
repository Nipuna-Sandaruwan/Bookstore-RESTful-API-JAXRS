package com.bookstore.service;

import com.bookstore.exception.BookNotFoundException;
import com.bookstore.exception.CartNotFoundException;
import com.bookstore.exception.CustomerNotFoundException;
import com.bookstore.exception.OutOfStockException;
import com.bookstore.model.Book;
import com.bookstore.model.Cart;
import com.bookstore.model.CartItem;
import com.bookstore.model.Order;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class OrderService {
    private static Map<String, List<Order>> customerOrders = new HashMap<>();
    private BookService bookService = new BookService();
    private CustomerService customerService = new CustomerService();
    private CartService cartService = new CartService();

    public Order createOrder(String customerId) {
        // Check if customer exists
        customerService.getCustomer(customerId);
        
        Cart cart = cartService.getCart(customerId);
        if (cart.getItems().isEmpty()) {
            throw new CartNotFoundException("Cart is empty");
        }
        
        // Check stock and calculate total
        double total = 0;
        for (CartItem item : cart.getItems()) {
            Book book = bookService.getBook(item.getBookId());
            if (book.getStockQuantity() < item.getQuantity()) {
                throw new OutOfStockException("Not enough stock for book " + book.getTitle());
            }
            total += book.getPrice() * item.getQuantity();
        }
        
        // Update stock
        for (CartItem item : cart.getItems()) {
            Book book = bookService.getBook(item.getBookId());
            book.setStockQuantity(book.getStockQuantity() - item.getQuantity());
            bookService.updateBook(book.getId(), book);
        }
        
        // Create order
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setCustomerId(customerId);
        order.setOrderDate(new Date());
        order.setTotalAmount(total);
        order.getItems().addAll(cart.getItems());
        
        // Clear cart
        cart.getItems().clear();
        
        // Add to customer's orders
        List<Order> orders = customerOrders.getOrDefault(customerId, new ArrayList<>());
        orders.add(order);
        customerOrders.put(customerId, orders);
        
        return order;
    }

    public List<Order> getCustomerOrders(String customerId) {
        // Check if customer exists
        customerService.getCustomer(customerId);
        
        return customerOrders.getOrDefault(customerId, new ArrayList<>());
    }

    public Order getCustomerOrder(String customerId, String orderId) {
        // Check if customer exists
        customerService.getCustomer(customerId);
        
        List<Order> orders = customerOrders.getOrDefault(customerId, new ArrayList<>());
        for (Order order : orders) {
            if (order.getId().equals(orderId)) {
                return order;
            }
        }
        throw new CartNotFoundException("Order with id " + orderId + " not found");
    }
}