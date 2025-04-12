package org.example.springproperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class GreetingController {

    private final SinglePropertieService singlePropertieService;
    private final MultiplePropertiesService multiplePropertiesService;

    @Autowired
    public GreetingController(SinglePropertieService singlePropertieService, MultiplePropertiesService multiplePropertiesService) {
        this.singlePropertieService = singlePropertieService;
        this.multiplePropertiesService = multiplePropertiesService;
    }

    @GetMapping("/greeting")
    public String getGreeting() {
        return singlePropertieService.getGreeting();
    }

    @GetMapping("/greetings")
    public HashMap<String, String> getGreetings() {
        HashMap<String, String> map = new HashMap<>();
        map.put("deutsch", multiplePropertiesService.getGerman());
        map.put("englisch", multiplePropertiesService.getEnglish());
        return map;
    }
}
