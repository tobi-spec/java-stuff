package org.example.springjwt.symmetric;

import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
public class JwtShardSecretController {

    JwtSharedSecretService jwtServiceSharedService;

    @Autowired
    public JwtShardSecretController(JwtSharedSecretService jwtServiceSharedService) {
        this.jwtServiceSharedService = jwtServiceSharedService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username) throws JOSEException {
        return jwtServiceSharedService.createJWTToken(username);
    }

    @GetMapping("/secure-data")
    public String getSecureData(@RequestHeader("Authorization") String header) throws ParseException, JOSEException {
        String token = jwtServiceSharedService.extractToken(header);
        String username = jwtServiceSharedService.validateToken(token);
        return "This is secure data for user: " + username;

    }

    @PostMapping("/logout")
    public String logout() {
        return "Client must discard the token. No server-side session to invalidate.";
    }


}
