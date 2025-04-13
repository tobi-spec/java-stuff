package org.example.springjwt;

import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
public class JWTController {

    JWTService jwtService;

    @Autowired
    public JWTController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username) throws JOSEException {
        return jwtService.createJWTToken(username);
    }

    @GetMapping("/secure-data")
    public String getSecureData(@RequestHeader("Authorization") String header) throws ParseException, JOSEException {
        String token = jwtService.extractToken(header);
        String username = jwtService.validateToken(token);
        return "This is secure data for user: " + username;

    }

    @PostMapping("/logout")
    public String logout() {
        return "Client must discard the token. No server-side session to invalidate.";
    }


}
