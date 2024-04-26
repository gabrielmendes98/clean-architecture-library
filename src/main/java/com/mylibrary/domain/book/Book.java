package com.mylibrary.domain.book;

import com.mylibrary.domain.Entity;
import com.mylibrary.domain.author.AuthorID;
import com.mylibrary.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class Book extends Entity<BookID> {
    private final String title;
    private final String description;
    private final BookStatus status;
    private final Instant createdAt;
    private final AuthorID authorID;

    private Book(
            BookID bookID,
            String title,
            String description,
            BookStatus status,
            Instant createdAt,
            AuthorID authorID
    ) {
        super(bookID);
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");
        this.authorID = authorID;
    }

    public static Book create(String title, String description, AuthorID authorID) {
        final var id = BookID.unique();
        final var createdAt = Instant.now();
        return new Book(id, title, description, BookStatus.AVAILABLE, createdAt, authorID);
    }

    public static Book with(BookID id, String title, String description, BookStatus status, Instant createdAt, AuthorID authorID) {
        return new Book(id, title, description, status, createdAt, authorID);
    }

    @Override
    public void validate(ValidationHandler handler) {

    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public BookStatus getStatus() {
        return status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public AuthorID getAuthorID() {
        return authorID;
    }
}
