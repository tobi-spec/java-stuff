package com.example.springrestwebhook1;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/process")
    public ResponseEntity<String> process(@RequestBody UserRequest userRequest) {
            HttpStatusCode statusCode = userService.sendWebhook(userRequest.getWebhookUrl());
            if(statusCode != HttpStatus.OK) {
                return new ResponseEntity<>("Could not send webhook for userId" + userRequest.getUserId(), HttpStatus.OK);
            }
            return new ResponseEntity<>("Send webhook for userId " + userRequest.getUserId() ,HttpStatus.OK);
    }
}
