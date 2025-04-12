package org.example.springproperties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class SinglePropertieService {

    @Value("${app.greeting}")
    private String greetingMessage;

    public String getGreeting() {
        return greetingMessage;
    }
}
