package org.example.springvalidation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testUserOK() {
        String url = "http://localhost:" + port + "/user";
        UserDto userDto = new UserDto();
        userDto.setUsername("test");
        ResponseEntity<String> response = testRestTemplate.postForEntity(url, userDto, String.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void testUserToLong() {
        String url = "http://localhost:" + port + "/user";
        UserDto userDto = new UserDto();
        userDto.setUsername("testuser");
        ResponseEntity<String> response = testRestTemplate.postForEntity(url, userDto, String.class);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
