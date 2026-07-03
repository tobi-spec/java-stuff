package com.example.springrestwebhook1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// For this test, spring-rest-webhook-2 must be running
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringRestWebhookIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testWebhook() {
        String url = "http://localhost:" + port + "/process";
        UserRequest userRequest = new UserRequest("MyUser", "/webhook");

        HttpEntity httpEntity = new HttpEntity(userRequest);
        ResponseEntity<String> response = testRestTemplate.postForEntity(url, httpEntity, String.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
