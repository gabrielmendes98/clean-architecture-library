package com.mylibrary.application.user.create;

public record CreateUserInput(
        String name,
        String document,
        String password
) {
}
