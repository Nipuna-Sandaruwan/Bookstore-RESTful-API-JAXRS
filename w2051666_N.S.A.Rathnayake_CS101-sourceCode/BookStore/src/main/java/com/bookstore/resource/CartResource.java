package com.bookstore.resource;

import com.bookstore.model.Cart;
import com.bookstore.model.CartItem;
import com.bookstore.service.CartService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers/{customerId}/cart")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CartResource {
    private CartService cartService = new CartService();

    @POST
    @Path("/items")
    public Cart addItemToCart(@PathParam("customerId") String customerId, CartItem item) {
        return cartService.addItemToCart(customerId, item);
    }

    @GET
    public Cart getCart(@PathParam("customerId") String customerId) {
        return cartService.getCart(customerId);
    }

    @PUT
    @Path("/items/{bookId}")
    public Cart updateCartItem(
            @PathParam("customerId") String customerId,
            @PathParam("bookId") String bookId,
            CartItem item) {
        return cartService.updateCartItem(customerId, bookId, item.getQuantity());
    }

    @DELETE
    @Path("/items/{bookId}")
    public Response removeItemFromCart(
            @PathParam("customerId") String customerId,
            @PathParam("bookId") String bookId) {
        cartService.removeItemFromCart(customerId, bookId);
        return Response.noContent().build();
    }
}