package com.example.rediscaching;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-pg.properties")
public class RedisCachingIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testAddProduct()  {

        Product product = new Product("sqlSoap");
        HttpEntity<Product> entity = new HttpEntity<>(product);

        ResponseEntity<String> responseCreate = testRestTemplate.postForEntity(
                "http://localhost:" + port + "/create", entity, String.class);
        Assertions.assertEquals( HttpStatus.OK, responseCreate.getStatusCode());

        var time1 = System.currentTimeMillis();
        ResponseEntity<String> responseRead = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/read", String.class);
        Assertions.assertEquals(HttpStatus.OK, responseRead.getStatusCode());
        var time2 = System.currentTimeMillis();

        System.out.println("Time to read product information without cache: " + (time2 - time1) + "ms");

        var time3 = System.currentTimeMillis();
        ResponseEntity<String> responseRead2 = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/read", String.class);
        Assertions.assertEquals(HttpStatus.OK, responseRead2.getStatusCode());
        var time4 = System.currentTimeMillis();

        System.out.println("Time to read product information with cache: " + (time3 - time4) + "ms");
    }
}
