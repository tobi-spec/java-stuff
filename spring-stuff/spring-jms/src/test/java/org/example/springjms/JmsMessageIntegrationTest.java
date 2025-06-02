package org.example.springjms;

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
class JmsMessageIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void testMessaging() {
        String url = "http://localhost:" + port + "/message";

        JmsMessage jmsMessage = new JmsMessage();
        jmsMessage.setMessage("This is a Test Message");

        HttpEntity<JmsMessage> httpEntity = new HttpEntity(jmsMessage);
        ResponseEntity<Void> requestEntity = testRestTemplate.postForObject(url, httpEntity, ResponseEntity.class);

        Assertions.assertEquals(HttpStatus.OK, requestEntity.getStatusCode());

    }
}
