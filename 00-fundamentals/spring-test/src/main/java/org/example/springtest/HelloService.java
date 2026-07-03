package org.example.springtest;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String greet() {
        return "Hello from Spring";
    }
}
