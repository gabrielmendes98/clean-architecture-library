package com.mylibrary.application.auth.authenticate;

public record AuthenticateInput(
        String document,
        String password
) {
}
