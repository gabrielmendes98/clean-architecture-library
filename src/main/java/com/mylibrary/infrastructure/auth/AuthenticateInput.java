package com.mylibrary.infrastructure.auth;

public record AuthenticateInput(
        String document,
        String password
) {
}
