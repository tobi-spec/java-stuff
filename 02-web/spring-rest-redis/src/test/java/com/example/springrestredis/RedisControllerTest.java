package com.example.springrestredis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-redis.properties")
public class RedisControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testRedis() {
        String key = "testKey";
        String value = "testValue";

        String url = "http://localhost:" + port + "/redis/save?key=" + key + "&value=" + value;

        ResponseEntity<String> saveResponse = restTemplate.postForEntity(
               url, null, String.class);
        Assertions.assertEquals(HttpStatus.OK, saveResponse.getStatusCode());

        ResponseEntity<String> readResponse = restTemplate.getForEntity(
                "http://localhost:" + port + "/redis/read?key=" + key, String.class);
        Assertions.assertEquals(HttpStatus.OK, readResponse.getStatusCode());
        Assertions.assertEquals(value, readResponse.getBody());
    }
}
