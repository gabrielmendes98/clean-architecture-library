package com.mylibrary.domain.author;

import com.mylibrary.domain.Entity;
import com.mylibrary.domain.validation.ValidationHandler;
import com.mylibrary.domain.valueobjects.PersonName;

import java.time.Instant;
import java.util.Objects;

public class Author extends Entity<AuthorID> {
    private final Instant createdAt;
    private PersonName name;

    private Author(AuthorID id, PersonName name, Instant createdAt) {
        super(id);
        this.name = name;
        this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");

    }

    public static Author create(PersonName name) {
        final var id = AuthorID.unique();
        final var createdAt = Instant.now();
        return new Author(id, name, createdAt);
    }

    public static Author with(AuthorID id, PersonName name, Instant createdAt) {
        return new Author(id, name, createdAt);
    }

    @Override
    public AuthorID getId() {
        return id;
    }

    public PersonName getName() {
        return name;
    }

    public void setName(PersonName name) {
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
