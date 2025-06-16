package com.example.springrestwebhook2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebHookController {

    @GetMapping("/webhook")
    public ResponseEntity<String> receiveWebhook() {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
