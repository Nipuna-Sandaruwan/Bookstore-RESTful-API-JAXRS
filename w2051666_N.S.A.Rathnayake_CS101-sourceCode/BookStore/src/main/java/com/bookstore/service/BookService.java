package com.bookstore.service;

import com.bookstore.exception.BookNotFoundException;
import com.bookstore.exception.InvalidInputException;
import com.bookstore.model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookService {
    private static Map<String, Book> books = new HashMap<>();
    private static int idCounter = 1;

    public List<Book> getAllBooks() {
        System.out.println("get All books");
        return new ArrayList<>(books.values());
    }

    public Book getBook(String id) {
        Book book = books.get(id);
        if (book == null) {
            throw new BookNotFoundException("Book with id " + id + " not found");
        }
        return book;
    }

    public Book addBook(Book book) {
        validateBook(book);
        book.setId("book-" + idCounter++);
        books.put(book.getId(), book);
        return book;
    }

    public Book updateBook(String id, Book book) {
        if (!books.containsKey(id)) {
            throw new BookNotFoundException("Book with id " + id + " not found");
        }
        validateBook(book);
        book.setId(id);
        books.put(id, book);
        return book;
    }

    public void deleteBook(String id) {
        if (!books.containsKey(id)) {
            throw new BookNotFoundException("Book with id " + id + " not found");
        }
        books.remove(id);
    }

    private void validateBook(Book book) {
        if (book.getPublicationYear() > 2025) {
            throw new InvalidInputException("Publication year cannot exceed 2025.");
        }
        if (book.getPrice() < 0) {
            throw new InvalidInputException("Price cannot be negative.");
        }
        if (book.getStockQuantity() < 0) {
            throw new InvalidInputException("Stock quantity cannot be negative.");
        }
    }
}
