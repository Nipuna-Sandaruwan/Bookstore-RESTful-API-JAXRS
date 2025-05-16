package com.bookstore.resource;

import com.bookstore.model.Customer;
import com.bookstore.service.CustomerService;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {
    private CustomerService customerService = new CustomerService();

    @POST
    public Response addCustomer(Customer customer) {
        Customer newCustomer = customerService.addCustomer(customer);
        return Response.status(Response.Status.CREATED).entity(newCustomer).build();
    }

    @GET
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GET
    @Path("/{id}")
    public Customer getCustomer(@PathParam("id") String id) {
        return customerService.getCustomer(id);
    }

    @PUT
    @Path("/{id}")
    public Customer updateCustomer(@PathParam("id") String id, Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") String id) {
        customerService.deleteCustomer(id);
        return Response.noContent().build();
    }
}