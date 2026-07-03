package org.example.springproperties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "greetings")
public class MultiplePropertiesService {
    private String german;
    private String english;

    public String getGerman() {
        return german;
    }

    public void setGerman(String german) {
        this.german = german;
    }


    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }


}
