package com.bookstore.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<RuntimeException> {
    @Override
    public Response toResponse(RuntimeException exception) {
        Map<String, String> errorResponse = new HashMap<>();

        if (exception instanceof BookNotFoundException) {
            errorResponse.put("error", "Book Not Found");
            errorResponse.put("message", exception.getMessage());
            return buildResponse(Response.Status.NOT_FOUND, errorResponse);
        } else if (exception instanceof AuthorNotFoundException) {
            errorResponse.put("error", "Author Not Found");
            errorResponse.put("message", exception.getMessage());
            return buildResponse(Response.Status.NOT_FOUND, errorResponse);
        } else if (exception instanceof CustomerNotFoundException) {
            errorResponse.put("error", "Customer Not Found");
            errorResponse.put("message", exception.getMessage());
            return buildResponse(Response.Status.NOT_FOUND, errorResponse);
        } else if (exception instanceof InvalidInputException) {
            errorResponse.put("error", "Invalid Input");
            errorResponse.put("message", exception.getMessage());
            return buildResponse(Response.Status.BAD_REQUEST, errorResponse);
        } else if (exception instanceof OutOfStockException) {
            errorResponse.put("error", "Out of Stock");
            errorResponse.put("message", exception.getMessage());
            return buildResponse(Response.Status.BAD_REQUEST, errorResponse);
        } else if (exception instanceof CartNotFoundException) {
            errorResponse.put("error", "Cart Not Found");
            errorResponse.put("message", exception.getMessage());
            return buildResponse(Response.Status.NOT_FOUND, errorResponse);
        } else {
            errorResponse.put("error", "Internal Server Error");
            errorResponse.put("message", "An unexpected error occurred.");
            return buildResponse(Response.Status.INTERNAL_SERVER_ERROR, errorResponse);
        }
    }

    private Response buildResponse(Response.Status status, Map<String, String> error) {
        return Response.status(status)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
