package com.bookstore.resource;

import com.bookstore.model.Author;
import com.bookstore.model.Book;
import com.bookstore.service.AuthorService;
import com.bookstore.service.BookService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {
    private AuthorService authorService = new AuthorService();
    private BookService bookService = new BookService();

    @POST
    public Response addAuthor(Author author) {
        Author newAuthor = authorService.addAuthor(author);
        return Response.status(Response.Status.CREATED).entity(newAuthor).build();
    }

    @GET
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GET
    @Path("/{id}")
    public Author getAuthor(@PathParam("id") String id) {
        return authorService.getAuthor(id);
    }

    @PUT
    @Path("/{id}")
    public Author updateAuthor(@PathParam("id") String id, Author author) {
        return authorService.updateAuthor(id, author);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAuthor(@PathParam("id") String id) {
        authorService.deleteAuthor(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/{id}/books")
    public List<Book> getBooksByAuthor(@PathParam("id") String authorId) {
        return bookService.getAllBooks().stream()
                .filter(book -> book.getAuthorId().equals(authorId))
                .collect(Collectors.toList());
    }
}