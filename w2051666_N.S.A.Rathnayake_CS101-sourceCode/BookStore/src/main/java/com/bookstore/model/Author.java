package com.bookstore.model;

public class Author {
    private String id;
    private String name;
    private String biography;

    // Constructors, getters, and setters
    public Author() {}

    public Author(String id, String name, String biography) {
        this.id = id;
        this.name = name;
        this.biography = biography;
    }

    // Getters and setters for all fields
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBiography() { return biography; }
    public void setBiography(String biography) { this.biography = biography; }
}