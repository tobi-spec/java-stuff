package org.example.springprofil;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("dev")
public class DevGreetingService implements GreetingService{

    @Override
    public String greet() {
        return "Hello from Dev!";
    }
}
