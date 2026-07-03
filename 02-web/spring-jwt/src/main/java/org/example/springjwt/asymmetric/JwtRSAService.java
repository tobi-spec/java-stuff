package org.example.springjwt.asymmetric;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.ParseException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtRSAService {

    @Value("${jwt.privat}")
    private String privateKeyBase64;

    @Value("${jwt.public}")
    private String publicKeyBase64;

    private static final long EXPIRATION_TIME_MS = 60 * 60 * 1000; // 1 hour


    public String createJWTToken(String username) throws JOSEException, ParseException, NoSuchAlgorithmException, InvalidKeySpecException {
        JWSHeader header = new JWSHeader(JWSAlgorithm.RS256);
        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .audience("SpringTest")
                .subject(username)
                .issuer("SpringBootBackend")
                .expirationTime(new Date(System.currentTimeMillis()+ EXPIRATION_TIME_MS))
                .build();

        SignedJWT jwt = new SignedJWT(header, claims);

        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyBase64);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

        jwt.sign(new RSASSASigner(privateKey));

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

    public String validateToken(String token) throws ParseException, JOSEException, InvalidKeySpecException, NoSuchAlgorithmException {
        SignedJWT jwt = SignedJWT.parse(token);

        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyBase64);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);

        if(!jwt.verify(new RSASSAVerifier((RSAPublicKey) publicKey))) {
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
