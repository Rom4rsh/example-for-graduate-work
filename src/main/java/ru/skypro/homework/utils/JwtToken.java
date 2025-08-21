package ru.skypro.homework.utils;

import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtToken {
    @Value("${jwt.secret}")
    private String SECRET;
    @Value("${jwt.expiration}")
    private long EXPIRATION_TIME;

    // генерация токена
    public String generateToken(String username) {
        try {
            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(username)
                    .issueTime(new Date())
                    .expirationTime(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .build();

            JWSSigner signer = new MACSigner(SECRET.getBytes());

            SignedJWT signedJWT = new SignedJWT(
                    new JWSHeader(JWSAlgorithm.HS256),
                    claims
            );

            signedJWT.sign(signer);

            return signedJWT.serialize(); // готовый JWT
        } catch (Exception e) {
            throw new RuntimeException("Error generating JWT", e);
        }
    }

    // валидация токена
    public boolean validateToken(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SECRET.getBytes());

            return signedJWT.verify(verifier)
                    && new Date().before(signedJWT.getJWTClaimsSet().getExpirationTime());
        } catch (Exception e) {
            return false;
        }
    }

    //получение username по токену
    public String getUsername(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }
}
