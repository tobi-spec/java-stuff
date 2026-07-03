package com.example.springsecurityrole;

import org.apache.coyote.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RoleControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    int port;

    @Test
    public void testAdminEndpointWithRoleAdmin() {
        String url = "http://localhost:" + port + "/admin";
        ResponseEntity<String> response = this.restTemplate.withBasicAuth("admin", "admin").getForEntity(url, String.class);
        assertEquals("Hello, Admin", response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testAdminEndpointWithRoleUser() {
        String url = "http://localhost:" + port + "/admin";
        ResponseEntity<String> response = this.restTemplate.withBasicAuth("user", "user").getForEntity(url, String.class);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }
}
