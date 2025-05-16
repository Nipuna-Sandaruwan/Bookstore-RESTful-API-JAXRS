package com.bookstore.config;

import com.bookstore.exception.CustomExceptionMapper;
import com.bookstore.resource.AuthorResource;
import com.bookstore.resource.BookResource;
import com.bookstore.resource.CartResource;
import com.bookstore.resource.CustomerResource;
import com.bookstore.resource.OrderResource;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class ApplicationConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(BookResource.class);
        resources.add(AuthorResource.class);
        resources.add(CustomerResource.class);
        resources.add(CartResource.class);
        resources.add(OrderResource.class);
        resources.add(CustomExceptionMapper.class);
        return resources;
    }
}