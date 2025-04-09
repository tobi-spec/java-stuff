package org.example.springproperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    private final SingleGreetingService singleGreetingService;

    @Autowired
    public GreetingController(SingleGreetingService singleGreetingService) {
        this.singleGreetingService = singleGreetingService;
    }

    @GetMapping("/greeting")
    public String getGreeting() {
        return singleGreetingService.getGreeting();
    }
}
