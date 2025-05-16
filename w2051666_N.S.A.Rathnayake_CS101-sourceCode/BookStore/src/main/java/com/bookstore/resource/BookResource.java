package com.bookstore.resource;

import com.bookstore.model.Book;
import com.bookstore.service.BookService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {
    private BookService bookService = new BookService();

    @POST
    public Response addBook(Book book) {
        Book newBook = bookService.addBook(book);
        return Response.status(Response.Status.CREATED).entity(newBook).build();
    }

    @GET
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GET
    @Path("/{id}")
    public Book getBook(@PathParam("id") String id) {
        return bookService.getBook(id);
    }

    @PUT
    @Path("/{id}")
    public Book updateBook(@PathParam("id") String id, Book book) {
        return bookService.updateBook(id, book);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") String id) {
        bookService.deleteBook(id);
        return Response.noContent().build();
    }
}