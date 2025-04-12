package org.example.springproperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class GreetingController {

    private final SingleGreetingService singleGreetingService;
    private final MultiplePropertiesService multiplePropertiesService;

    @Autowired
    public GreetingController(SingleGreetingService singleGreetingService, MultiplePropertiesService multiplePropertiesService) {
        this.singleGreetingService = singleGreetingService;
        this.multiplePropertiesService = multiplePropertiesService;
    }

    @GetMapping("/greeting")
    public String getGreeting() {
        return singleGreetingService.getGreeting();
    }

    @GetMapping("/greetings")
    public HashMap<String, String> getGreetings() {
        HashMap<String, String> map = new HashMap<>();
        map.put("deutsch", multiplePropertiesService.getGerman());
        map.put("englisch", multiplePropertiesService.getEnglish());
        return map;
    }
}
