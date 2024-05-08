package com.mylibrary.domain.auth;

public interface TokenService {
    String generateToken(String subject);

    String validateToken(String token);
}
