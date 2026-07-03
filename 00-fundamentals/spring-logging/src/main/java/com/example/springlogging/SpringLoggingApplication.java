package com.example.springlogging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringLoggingApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringLoggingApplication.class, args);
        ServiceWithSJF4J serviceWithSJF4J = context.getBean(ServiceWithSJF4J.class);
        serviceWithSJF4J.helloFromSJF4J();
    }

}
