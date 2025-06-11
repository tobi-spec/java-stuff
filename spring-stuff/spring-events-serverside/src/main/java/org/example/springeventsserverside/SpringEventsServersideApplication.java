package org.example.springeventsserverside;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringEventsServersideApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringEventsServersideApplication.class, args);
    }

}
