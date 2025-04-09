package org.example.springproperties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SingleGreetingService {

    @Value("${app.greeting}")
    private String greetingMessage;

    public String getGreeting() {
        return greetingMessage;
    }
}
