package com.mylibrary.application.user.admin.create;

import com.mylibrary.domain.user.UserRole;

public record AdminCreateUserInput(
        String name,
        String document,
        UserRole role
) {
}
