package org.example.springrestsql;

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
public class ProductControllerPostgresIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testAddProduct() {
        Product product = new Product("sqlSoap");
        HttpEntity<Product> entity = new HttpEntity<>(product);

        ResponseEntity<String> responseCreate = testRestTemplate.postForEntity(
                "http://localhost:" + port + "/create", entity, String.class);
        Assertions.assertEquals( HttpStatus.CREATED, responseCreate.getStatusCode());

        ResponseEntity<String> responseRead = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/read", String.class);
        Assertions.assertEquals(HttpStatus.OK, responseRead.getStatusCode());
        Assertions.assertEquals(responseRead.getBody(), new Product("soap"));


    }



}
