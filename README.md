# 📚 Bookstore RESTful API with JAX-RS

This project is a RESTful backend API for a Bookstore application built using Java and JAX-RS. It simulates a real-world e-commerce system backend that manages books, authors, customers, carts, and orders.

## 📌 Overview

The Bookstore API is designed following REST architecture and demonstrates key backend concepts such as:
- Resource-based routing
- HTTP methods (GET, POST, PUT, DELETE)
- In-memory data storage
- Structured JSON request/response handling
- Exception handling and input validation

It is built without any external databases or frameworks, focusing purely on Java's capabilities with JAX-RS.

---

## 🛠️ Technologies Used

- **Java** (JDK 17+ recommended)
- **JAX-RS** (e.g., Jersey or RESTEasy)
- **JSON** for request/response format
- **Postman** for API testing
- **In-Memory Data Structures** (e.g., `ArrayList`, `HashMap`)

---

## 📁 Project Structure

src/
├── model/ # Java classes for Book, Author, Customer, etc.
├── resource/ # RESTful API resource classes
├── exception/ # Custom exceptions and mappers
└── main/ # Main application class


---

## 🔑 API Endpoints

### 📘 Book Management
- `POST /books` – Add a new book
- `GET /books` – Get all books
- `GET /books/{id}` – Get a book by ID
- `PUT /books/{id}` – Update book information
- `DELETE /books/{id}` – Delete a book

### ✍️ Author Management
- `POST /authors` – Add a new author
- `GET /authors` – Get all authors
- `GET /authors/{id}` – Get an author by ID
- `PUT /authors/{id}` – Update author info
- `DELETE /authors/{id}` – Delete an author
- `GET /authors/{id}/books` – Get books by author

### 👤 Customer Management
- `POST /customers` – Create a new customer
- `GET /customers` – Get all customers
- `GET /customers/{id}` – Get customer by ID
- `PUT /customers/{id}` – Update customer info
- `DELETE /customers/{id}` – Delete a customer

### 🛒 Shopping Cart
- `POST /customers/{customerId}/cart/items` – Add a book to the cart
- `GET /customers/{customerId}/cart` – View cart
- `PUT /customers/{customerId}/cart/items/{bookId}` – Update quantity
- `DELETE /customers/{customerId}/cart/items/{bookId}` – Remove item from cart

### 🧾 Orders
- `POST /customers/{customerId}/orders` – Place an order
- `GET /customers/{customerId}/orders` – View all orders
- `GET /customers/{customerId}/orders/{orderId}` – View a specific order

---

## ⚙️ Error Handling

The API handles errors gracefully with custom exception classes and meaningful JSON error responses.

Handled scenarios include:
- Book/Author/Customer not found
- Invalid inputs
- Out-of-stock items
- Cart-related errors

Each exception returns an appropriate HTTP status code and a clear error message in the response body.

---

## 🧪 Testing

All endpoints were thoroughly tested using **Postman** with:
- Valid inputs
- Invalid inputs
- Edge cases
- Error triggers for exceptions

Testing covers all CRUD operations and business logic validation.

---

## 🎥 Demonstration

A short video demonstration is included in the submission, showcasing:
- Endpoint usage through Postman
- Sample inputs and expected responses
- Success and failure scenarios

- Demonstration & Explanaton  video link - https://drive.google.com/file/d/13I7y0M1QvDJ9iag7Ahg8TRhkskZzMLxg/view?usp=sharing


---

## 💡 Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/Bookstore-RESTful-API-JAXRS.git

