package org.example.springsecurityjwtauth;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class JwtService {

    private static final String SECRET = "MyJwtSecret";
    private static final long EXPIRATION_TIME_MS = 60 * 60 * 1000; // 1 hour

    public String createJwtToken(String username) throws JOSEException {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .audience("SpringSecurityTest")
                .subject(username)
                .issuer("SpringBootBackend")
                .expirationTime(new Date(System.currentTimeMillis() + EXPIRATION_TIME_MS))
                .build();

        SignedJWT jwt = new SignedJWT(header, claims);
        jwt.sign(new MACSigner(SECRET));

        return jwt.serialize();
    }

    public String extractToken(String header) {
        if(header == null || !header.startsWith("Bearer ")) {
            throw new UnauthorizedAccessException();
        }
        return header.substring(7);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    static class UnauthorizedAccessException extends RuntimeException {
    }

    public String extractUsername(String token) throws ParseException {
        SignedJWT jwt = SignedJWT.parse(token);
        return jwt.getJWTClaimsSet().getSubject();
    }

    public boolean validateToken(String token, UserDetails userDetails) throws ParseException, JOSEException {
        SignedJWT jwt = SignedJWT.parse(token);
        if(!jwt.verify(new MACVerifier(SECRET))) {
            throw new IllegalArgumentException("Invalid Signature");
        }
        Date expirationTime = jwt.getJWTClaimsSet().getExpirationTime();
        if(expirationTime.before(new Date())) {
            throw new IllegalArgumentException("Token expired");
        }
        String issuer = jwt.getJWTClaimsSet().getIssuer();
        if(!"SpringBootBackend".equals(issuer)) {
            throw new IllegalArgumentException("Wrong Issuer");
        }
        List<String> audience = jwt.getJWTClaimsSet().getAudience();
        if(!audience.contains("SpringTest")) {
            throw new IllegalArgumentException("Wrong audience");
        }
        String subject = jwt.getJWTClaimsSet().getSubject();
        if(!subject.equals(userDetails.getUsername())) {
            throw new IllegalArgumentException("Wrong username");
        }
        return true;
    }
}
