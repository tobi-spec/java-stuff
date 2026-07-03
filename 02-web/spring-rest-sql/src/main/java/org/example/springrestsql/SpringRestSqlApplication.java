package org.example.springrestsql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SpringRestSqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestSqlApplication.class, args);
    }

}
