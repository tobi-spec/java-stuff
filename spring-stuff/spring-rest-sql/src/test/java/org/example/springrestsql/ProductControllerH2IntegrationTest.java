package org.example.springrestsql;

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

import java.util.List;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-h2.properties")
class ProductControllerH2IntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void testCreateAndGetProduct() throws JsonProcessingException {
        Product product1 = new Product("h2Soap");
        HttpEntity<Product> entity1 = new HttpEntity<>(product1);

        ResponseEntity<String> responseCreate1 = testRestTemplate.postForEntity(
                "http://localhost:" + port + "/create", entity1, String.class);
        Assertions.assertEquals( HttpStatus.CREATED, responseCreate1.getStatusCode());

        ResponseEntity<String> responseRead = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/read", String.class);
        Assertions.assertEquals(HttpStatus.OK, responseRead.getStatusCode());

        List<Product> actualList = objectMapper.readValue(responseRead.getBody(), new TypeReference<List<Product>>() {});
        List<Product> expectedList = List.of(
                new Product("h2Soap")
        );
        Assertions.assertEquals(expectedList.size(), actualList.size());
        List<String> actualNames = actualList.stream().map(Product::getName).toList();
        Assertions.assertTrue(actualNames.contains("h2Soap"));
    }
}
