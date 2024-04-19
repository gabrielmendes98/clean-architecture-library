package com.mylibrary.domain.author;

import com.mylibrary.domain.Entity;
import com.mylibrary.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class Author extends Entity<AuthorID> {
    private final AuthorID id;
    private final Instant createdAt;
    private String name;

    private Author(AuthorID id, String name, Instant createdAt) {
        super(id);
        this.id = id;
        this.name = name;
        this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");

    }

    public static Author create(String name) {
        final var id = AuthorID.unique();
        final var createdAt = Instant.now();
        return new Author(id, name, createdAt);
    }

    @Override
    public AuthorID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public void validate(ValidationHandler handler) {
        new AuthorValidator(this, handler).validate();
    }
}
