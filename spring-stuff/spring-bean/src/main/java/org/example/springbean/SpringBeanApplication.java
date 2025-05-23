package org.example.springbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBeanApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBeanApplication.class, args);
        GreetingService greetingService = context.getBean(GreetingService.class);
        System.out.println(greetingService.greet("World"));
    }

}
