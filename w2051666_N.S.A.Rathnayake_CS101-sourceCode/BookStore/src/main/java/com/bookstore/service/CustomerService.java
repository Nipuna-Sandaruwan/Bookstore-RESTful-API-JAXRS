package com.bookstore.service;

import com.bookstore.exception.CustomerNotFoundException;
import com.bookstore.exception.InvalidInputException;
import com.bookstore.model.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomerService {
    private static Map<String, Customer> customers = new HashMap<>();
    private static int idCounter = 1;

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers.values());
    }

    public Customer getCustomer(String id) {
        Customer customer = customers.get(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with id " + id + " not found");
        }
        return customer;
    }

    public Customer addCustomer(Customer customer) {
        validateCustomer(customer);
        customer.setId("customer-" + idCounter++);
        customers.put(customer.getId(), customer);
        return customer;
    }

    public Customer updateCustomer(String id, Customer customer) {
        if (!customers.containsKey(id)) {
            throw new CustomerNotFoundException("Customer with id " + id + " not found");
        }
        validateCustomer(customer);
        customer.setId(id);
        customers.put(id, customer);
        return customer;
    }

    public void deleteCustomer(String id) {
        if (!customers.containsKey(id)) {
            throw new CustomerNotFoundException("Customer with id " + id + " not found");
        }
        customers.remove(id);
    }

    private void validateCustomer(Customer customer) {
        if (customer.getName() == null || !customer.getName().matches("^[A-Za-z ]+$")) {
            throw new InvalidInputException("Customer name must contain only letters and spaces.");
        }
        if (customer.getEmail() == null || !customer.getEmail().contains("@")) {
            throw new InvalidInputException("Invalid email address. Email must contain '@'.");
        }
        if (customer.getPassword() == null || customer.getPassword().trim().isEmpty()) {
            throw new InvalidInputException("Password cannot be empty.");
        }
    }
}
