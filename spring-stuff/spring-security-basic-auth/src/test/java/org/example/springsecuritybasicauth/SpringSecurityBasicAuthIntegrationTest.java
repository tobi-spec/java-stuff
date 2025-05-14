package org.example.springsecuritybasicauth;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("classpath:application-h2.properties")
class SpringSecurityBasicAuthIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        appUserRepository.deleteAll();
    }

    @Test
    void testLiveEndpoint() {
        String url = "http://localhost:" + port + "/live";
        String response = testRestTemplate.getForObject(url, String.class);
        Assertions.assertEquals("Server is live", response);
    }

    @Test
    void testRegisterUser() {
        String url = "http://localhost:" + port + "/register";

        RegisterRequest registerRequest = new RegisterRequest("testuser", "password");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegisterRequest> httpEntity = new HttpEntity<>(registerRequest, httpHeaders);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(url, httpEntity, String.class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals("User registered successfully", responseEntity.getBody());
    }

    @Test
    void testRegisterUserExists() {
        appUserRepository.save(new AppUser("testuser", passwordEncoder.encode("password")));
        String url = "http://localhost:" + port + "/register";

        RegisterRequest registerRequest = new RegisterRequest("testuser", "password");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegisterRequest> httpEntity = new HttpEntity<>(registerRequest, httpHeaders);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(url, httpEntity, String.class);

        Assertions.assertEquals(HttpStatus.CONFLICT, responseEntity.getStatusCode());
        Assertions.assertEquals("User already exists", responseEntity.getBody());
    }

    @Test
    void testLoginUserSuccessful() {
        appUserRepository.save(new AppUser("testuser", passwordEncoder.encode("password")));
        String url = "http://localhost:" + port + "/login";

        RegisterRequest registerRequest = new RegisterRequest("testuser", "password");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegisterRequest> httpEntity = new HttpEntity<>(registerRequest, httpHeaders);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(url, httpEntity, String.class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals("User logged in successfully", responseEntity.getBody());
    }

    @Test
    void testLoginUserFail() {
        appUserRepository.save(new AppUser("testuser", passwordEncoder.encode("password")));

        String url = "http://localhost:" + port + "/login";
        RegisterRequest registerRequest = new RegisterRequest("testuser", "wrongPassword");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegisterRequest> httpEntity = new HttpEntity<>(registerRequest, httpHeaders);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(url, httpEntity, String.class);

        Assertions.assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        Assertions.assertEquals("Invalid username or password", responseEntity.getBody());
    }
}
