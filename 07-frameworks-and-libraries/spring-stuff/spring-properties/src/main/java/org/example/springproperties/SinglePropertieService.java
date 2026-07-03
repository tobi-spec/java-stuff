package org.example.springproperties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class SinglePropertieService {

    private String greetingMessage;

    public SinglePropertieService(@Value("${app.greeting}") String greetingMessage) {
        this.greetingMessage = greetingMessage;
    }

    public String getGreeting() {
        return greetingMessage;
    }
}
