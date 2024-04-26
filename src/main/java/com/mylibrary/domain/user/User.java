package com.mylibrary.domain.user;

import com.mylibrary.domain.Entity;
import com.mylibrary.domain.validation.ValidationHandler;
import com.mylibrary.domain.valueobjects.PersonName;
import com.mylibrary.domain.valueobjects.password.Password;

import java.time.Instant;

public class User extends Entity<UserID> {
    private final PersonName name;
    // Document could be a value object, but since it is used just on user,
    // I decided to use string literal
    private final String document;
    private final Password password;
    private final UserRole role;
    private final Instant createdAt;

    private User(UserID id, PersonName name, String document, Password password, UserRole role, Instant createdAt) {
        super(id);
        this.name = name;
        this.document = document;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
    }

    public static User create(PersonName name, String document, Password password, UserRole role) {
        final var id = UserID.unique();
        final var createdAt = Instant.now();
        return new User(id, name, document, password, role, createdAt);
    }


    public static User with(UserID id, PersonName name, String document, Password password, UserRole role, Instant createdAt) {
        return new User(id, name, document, password, role, createdAt);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new UserValidator(this, handler).validate();
    }

    public PersonName getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public Password getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
}
