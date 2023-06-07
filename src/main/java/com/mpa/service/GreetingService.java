package com.mpa.service;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped // single bean instance is created for the app and used by all other beans that inject it.
public class GreetingService {

    public String greet(String name) {
        return "Hello, %s!".formatted(name);
    }
}
