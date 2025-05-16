package com.bookstore.resource;

import com.bookstore.model.Order;
import com.bookstore.service.OrderService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customers/{customerId}/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class OrderResource {
    private OrderService orderService = new OrderService();

    @POST
    public Order createOrder(@PathParam("customerId") String customerId) {
        return orderService.createOrder(customerId);
    }

    @GET
    public List<Order> getCustomerOrders(@PathParam("customerId") String customerId) {
        return orderService.getCustomerOrders(customerId);
    }

    @GET
    @Path("/{orderId}")
    public Order getCustomerOrder(
            @PathParam("customerId") String customerId,
            @PathParam("orderId") String orderId) {
        return orderService.getCustomerOrder(customerId, orderId);
    }
}