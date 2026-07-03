package org.example.springevents;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderControllerIntegrationTests {

    @LocalServerPort
    public int port;

    @Autowired
    TestRestTemplate testRestTemplate;


    @Test
    public void testCreateProduct() {
        String url = "http://localhost:" + port + "/order";
        Order order = new Order(1, "soup");

        HttpEntity httpEntity = new HttpEntity(order);

        ResponseEntity<Void> response = testRestTemplate.postForEntity(url, httpEntity, Void.class);
        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

}
