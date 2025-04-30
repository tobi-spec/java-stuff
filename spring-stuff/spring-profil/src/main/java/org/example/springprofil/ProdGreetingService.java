package org.example.springprofil;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("prod")
public class ProdGreetingService implements GreetingService{

    @Override
    public String greet() {
        return "Hello from Prod!";
    }
}
