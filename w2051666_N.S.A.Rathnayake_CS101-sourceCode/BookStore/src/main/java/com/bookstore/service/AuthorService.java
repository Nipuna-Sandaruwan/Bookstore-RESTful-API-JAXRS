package com.bookstore.service;

import com.bookstore.exception.AuthorNotFoundException;
import com.bookstore.exception.InvalidInputException;
import com.bookstore.model.Author;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthorService {
    private static Map<String, Author> authors = new HashMap<>();
    private static int idCounter = 1;

    public List<Author> getAllAuthors() {
        return new ArrayList<>(authors.values());
    }

    public Author getAuthor(String id) {
        Author author = authors.get(id);
        if (author == null) {
            throw new AuthorNotFoundException("Author with id " + id + " not found");
        }
        return author;
    }

    public Author addAuthor(Author author) {
        validateAuthor(author);
        author.setId("author-" + idCounter++);
        authors.put(author.getId(), author);
        return author;
    }

    public Author updateAuthor(String id, Author author) {
        if (!authors.containsKey(id)) {
            throw new AuthorNotFoundException("Author with id " + id + " not found");
        }
        validateAuthor(author);
        author.setId(id);
        authors.put(id, author);
        return author;
    }

    public void deleteAuthor(String id) {
        if (!authors.containsKey(id)) {
            throw new AuthorNotFoundException("Author with id " + id + " not found");
        }
        authors.remove(id);
    }

    private void validateAuthor(Author author) {
        if (author.getName() == null || !author.getName().matches("^[A-Za-z ]+$")) {
            throw new InvalidInputException("Author name must contain only letters and spaces.");
        }
        if (author.getBiography() == null || author.getBiography().trim().isEmpty()) {
            throw new InvalidInputException("Biography cannot be empty.");
        }
    }
}
