package com.example.springrestwebhook1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    RestTemplate restTemplate;

    @Autowired
    public UserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public HttpStatusCode sendWebhook(String webhookUrl) {
            ResponseEntity<Void> response = restTemplate.getForEntity("http://localhost:8081/" + webhookUrl, Void.class);
            return response.getStatusCode();
    }
}
