package org.example.springrestsql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
    private ProductRepository productRepository;

    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        productRepository.deleteAll();
        productRepository.save(new Product("soap"));
    }

    @Test
    void testCreateProduct() {
        Product product1 = new Product("shampoo");
        HttpEntity<Product> entity1 = new HttpEntity<>(product1);

        ResponseEntity<String> responseCreate1 = testRestTemplate.postForEntity(
                "http://localhost:" + port + "/create", entity1, String.class);
        Assertions.assertEquals( HttpStatus.CREATED, responseCreate1.getStatusCode());

        List<Product> products = productRepository.findAll();
        Assertions.assertEquals(2, products.size());
    }


    @Test
    void testGetProduct() throws JsonProcessingException {
        ResponseEntity<String> responseRead = testRestTemplate.getForEntity(
                "http://localhost:" + port + "/read", String.class);
        Assertions.assertEquals(HttpStatus.OK, responseRead.getStatusCode());

        List<Product> actualList = objectMapper.readValue(responseRead.getBody(), new TypeReference<List<Product>>() {});
        List<Product> expectedList = List.of(
                new Product("soap")
        );
        Assertions.assertEquals(expectedList.size(), actualList.size());
        List<String> actualNames = actualList.stream().map(Product::getName).toList();
        Assertions.assertTrue(actualNames.contains("soap"));
    }

    @Test
    void testChangeProduct() {
        ResponseEntity<Void> responseCreate1 = testRestTemplate.postForEntity(
                "http://localhost:" + port + "/update?id={id}&name={name}",
                null,
                Void.class,
                1L,
                "shampoo");
        Assertions.assertEquals( HttpStatus.NO_CONTENT, responseCreate1.getStatusCode());
        productRepository.findById(1L).ifPresent(product -> {
            Assertions.assertEquals("shampoo", product.getName());
        });
    }

    @Test
    void testDeleteProduct() {
        ResponseEntity<Void> responseDelete = testRestTemplate.exchange(
                "http://localhost:" + port + "/delete/{id}",
                org.springframework.http.HttpMethod.DELETE,
                null,
                Void.class,
                1L);
        Assertions.assertEquals(HttpStatus.NO_CONTENT, responseDelete.getStatusCode());

        List<Product> products = productRepository.findAll();
        Assertions.assertEquals(0, products.size());
    }
}
