package org.example.springproperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(MultiplePropertiesService.class)
public class SpringPropertiesApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringPropertiesApplication.class, args);
    }

}
