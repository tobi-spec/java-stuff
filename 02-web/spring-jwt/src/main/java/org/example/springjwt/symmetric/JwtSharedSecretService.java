package org.example.springjwt.symmetric;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class JwtSharedSecretService {

    @Value("${jwt.sharedSecret}")
    private String SECRET;

    private static final long EXPIRATION_TIME_MS = 60 * 60 * 1000; // 1 hour


    public String createJWTToken(String username) throws JOSEException {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256);
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .audience("SpringTest")
                .subject(username)
                .issuer("SpringBootBackend")
                .expirationTime(new Date(System.currentTimeMillis()+ EXPIRATION_TIME_MS))
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

    public String validateToken(String token) throws ParseException, JOSEException {
        SignedJWT jwt = SignedJWT.parse(token);
        if(!jwt.verify(new MACVerifier(SECRET))) {
            throw new IllegalArgumentException("Invalid Signature");
        }
        Date expirationTime = jwt.getJWTClaimsSet().getExpirationTime();
        if(expirationTime.before(new Date())) {
            throw new IllegalArgumentException("Token expired");
        }
        String issuer = jwt.getJWTClaimsSet().getIssuer();
        if (!"SpringBootBackend".equals(issuer)) {
            throw new IllegalArgumentException("Wrong Issuer");
        }
        List<String> audience = jwt.getJWTClaimsSet().getAudience();
        if(!audience.contains("SpringTest")) {
            throw new IllegalArgumentException("Wrong audience");
        }

        return jwt.getJWTClaimsSet().getSubject();
    }
}
