package com.mylibrary.domain.book;

import com.mylibrary.domain.Entity;
import com.mylibrary.domain.author.AuthorID;
import com.mylibrary.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class Book extends Entity<BookID> {
    private final String title;
    private final String description;
    private final Instant createdAt;
    private final AuthorID authorID;
    private Instant returnDate;
    private BookStatus status;

    private Book(
            BookID bookID,
            String title,
            String description,
            BookStatus status,
            Instant createdAt,
            AuthorID authorID, Instant returnDate
    ) {
        super(bookID);
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");
        this.authorID = authorID;
        this.returnDate = returnDate;
    }

    public static Book create(String title, String description, AuthorID authorID) {
        final var id = BookID.unique();
        final var createdAt = Instant.now();
        return new Book(id, title, description, BookStatus.AVAILABLE, createdAt, authorID, null);
    }

    public static Book with(BookID id, String title, String description, BookStatus status, Instant createdAt, AuthorID authorID, Instant returnDate) {
        return new Book(id, title, description, status, createdAt, authorID, returnDate);
    }

    @Override
    public void validate(ValidationHandler handler) {
        new BookValidator(this, handler).validate();
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

    public Instant getReturnDate() {
        return returnDate;
    }

    public void rentBook(Instant returnDate) {
        this.status = BookStatus.RENTED;
        this.returnDate = returnDate;
    }
}
