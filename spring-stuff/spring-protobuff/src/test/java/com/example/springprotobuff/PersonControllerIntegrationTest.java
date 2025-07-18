package com.example.springprotobuff;

import com.example.springprotobuff.proto.PersonProto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testGetPerson() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/x-protobuf");

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<PersonProto.Person> response = restTemplate.exchange("http://localhost:" + port + "/person", HttpMethod.GET, requestEntity, PersonProto.Person.class);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(1, response.getBody().getId());
        Assertions.assertEquals("Alice", response.getBody().getName());
        Assertions.assertEquals("alice@example.com", response.getBody().getEmail());
    }
}
