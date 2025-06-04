package org.example.springvalidation;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @PostMapping("/user")
    public ResponseEntity<String> sendUser(@RequestBody @Valid UserDto userDto) {
        return new ResponseEntity<>("User is valid", HttpStatus.OK);
    }
}
