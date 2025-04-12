package org.example.springproperties;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GreetingsControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void getGreeting() {
        ResponseEntity<String> response = testRestTemplate.getForEntity(
                "http://localhost:" + port +"/greeting", String.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assertions.assertEquals(response.getBody(), "Hello from @value");
    }

    @Test
    public void getGreetings() {
        ResponseEntity<HashMap> response = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/greetings", HashMap.class);
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        HashMap<String, String> expected = new HashMap<>();
        expected.put("german", "Hallo von den properties");
        expected.put("english", "Hello from properties");
        Assertions.assertEquals(response.getBody(), expected);
    }
}
