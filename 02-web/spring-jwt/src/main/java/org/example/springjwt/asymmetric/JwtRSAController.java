package org.example.springjwt.asymmetric;

import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;

@RestController
public class JwtRSAController {

    JwtRSAService jwtServiceRSAKey;

    @Autowired
    public JwtRSAController(JwtRSAService jwtServiceRSAKey) {
        this.jwtServiceRSAKey = jwtServiceRSAKey;
    }

    @PostMapping("/loginRSA")
    public String login(@RequestParam String username) throws JOSEException, ParseException, NoSuchAlgorithmException, InvalidKeySpecException {
        return jwtServiceRSAKey.createJWTToken(username);
    }

    @GetMapping("/secure-dataRSA")
    public String getSecureData(@RequestHeader("Authorization") String header) throws ParseException, JOSEException, InvalidKeySpecException, NoSuchAlgorithmException {
        String token = jwtServiceRSAKey.extractToken(header);
        String username = jwtServiceRSAKey.validateToken(token);
        return "This is secure data for user: " + username;

    }

    @PostMapping("/logoutRSA")
    public String logout() {
        return "Client must discard the token. No server-side session to invalidate.";
    }


}
