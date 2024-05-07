package com.mylibrary.infrastructure.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.mylibrary.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class TokenService {
    public final String ISSUER = "auth0-library";
    @Value("${api.security.token.expirationTime}")
    private long EXPIRATION_TIME; // 10 days
    @Value("${api.security.token.secret}")
    private String PRIVATE_KEY;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(PRIVATE_KEY);
            return JWT.create()
                    .withIssuer(ISSUER)
                    .withSubject(user.getDocument())
                    .withExpiresAt(this.getExpirationDate())
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error generating the token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            System.out.println(token);
            Algorithm algorithm = Algorithm.HMAC256(PRIVATE_KEY);
            return JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Invalid token", exception);
        }
    }

    private Instant getExpirationDate() {
        return Instant.now().plusSeconds(EXPIRATION_TIME);
    }
}