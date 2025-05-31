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
import org.springframework.util.LinkedMultiValueMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


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
    void testRegisterUserAlreadyExists() {
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

        var loginRequest = new LinkedMultiValueMap<String, String>();
        loginRequest.put("username", Collections.singletonList("testuser"));
        loginRequest.put("password", Collections.singletonList("password"));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<LinkedMultiValueMap<String, String>> httpEntity = new HttpEntity<>(loginRequest, httpHeaders);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(url, httpEntity, String.class);
        List<String> cookies = responseEntity.getHeaders().get(HttpHeaders.SET_COOKIE);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(cookies);
    }

    @Test
    void testLoginUserFail() {
        appUserRepository.save(new AppUser("testuser", passwordEncoder.encode("password")));
        String url = "http://localhost:" + port + "/login";

        var loginRequest = new LinkedMultiValueMap<String, String>();
        loginRequest.put("username", Collections.singletonList("testuser"));
        loginRequest.put("password", Collections.singletonList("wrongPassword"));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<LinkedMultiValueMap<String, String>> httpEntity = new HttpEntity<>(loginRequest, httpHeaders);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(url, httpEntity, String.class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        // TODO: check that no set-cookie header is in responseEntity
    }

    @Test
    void testRegisterAndLogin() {
        String registerUrl = "http://localhost:" + port + "/register";

        RegisterRequest registerRequest = new RegisterRequest("testuser", "password");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<RegisterRequest> httpEntity = new HttpEntity<>(registerRequest, httpHeaders);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(registerUrl, httpEntity, String.class);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals("User registered successfully", responseEntity.getBody());

        String loginUrl = "http://localhost:" + port + "/login";
        var loginRequest = new LinkedMultiValueMap<String, String>();
        loginRequest.put("username", Collections.singletonList("testuser"));
        loginRequest.put("password", Collections.singletonList("password"));

        HttpHeaders loginhttpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<LinkedMultiValueMap<String, String>> loginHttpEntity = new HttpEntity<>(loginRequest, loginhttpHeaders);

        ResponseEntity<String> loginResponseEntity = testRestTemplate.postForEntity(loginUrl, loginHttpEntity, String.class);
        List<String> cookies = loginResponseEntity.getHeaders().get(HttpHeaders.SET_COOKIE);

        Assertions.assertEquals(HttpStatus.OK, loginResponseEntity.getStatusCode());
        Assertions.assertNotNull(cookies);
    }

    @Test
    void testLoginAndCallSecureEndpoint() {
        appUserRepository.save(new AppUser("testuser", passwordEncoder.encode("password")));
        String url = "http://localhost:" + port + "/login";

        var loginRequest = new LinkedMultiValueMap<String, String>();
        loginRequest.put("username", Collections.singletonList("testuser"));
        loginRequest.put("password", Collections.singletonList("password"));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<LinkedMultiValueMap<String, String>> httpEntity = new HttpEntity<>(loginRequest, httpHeaders);

        ResponseEntity<String> responseEntity = testRestTemplate.postForEntity(url, httpEntity, String.class);
        List<String> cookies = responseEntity.getHeaders().get(HttpHeaders.SET_COOKIE);

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertNotNull(cookies);

        String secureEndpointUrl = "http://localhost:" + port + "/me";
        HttpHeaders headers = new HttpHeaders();

        var cookie = responseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE);
        headers.set("Cookie", Arrays.stream(cookie.split(";")).findFirst().get());

        HttpEntity<String> secureHttpEntity = new HttpEntity<>(headers);

        ResponseEntity<String> secureResponseEntity = testRestTemplate.exchange(
                secureEndpointUrl,
                HttpMethod.GET,
                secureHttpEntity,
                String.class);

        Assertions.assertEquals(HttpStatus.OK, secureResponseEntity.getStatusCode());
    }
}
