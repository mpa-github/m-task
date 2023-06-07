package com.mpa.controller;

import com.mpa.service.GreetingService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

// With Quarkus, there is no need to create an Application class. It’s supported, but not required.
// In addition, only one instance of the resource is created and not one per request.
// You can configure this using the different *Scoped annotations (ApplicationScoped, RequestScoped, etc).
@Path("/hello")
public class GreetingResource {

    //@Inject // field injection
    //GreetingService greetingService;

    private final GreetingService greetingService;

    @Inject // constructor injection
    public GreetingResource(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/greet/{name}")
    public String greeting(String name) {
        return greetingService.greet(name);
    }
}
