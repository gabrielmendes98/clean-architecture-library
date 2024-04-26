package com.mylibrary.infrastructure.book.persistence;

import com.mylibrary.domain.author.AuthorID;
import com.mylibrary.domain.book.Book;
import com.mylibrary.domain.book.BookID;
import com.mylibrary.domain.book.BookStatus;
import com.mylibrary.infrastructure.author.persistence.AuthorJpaEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity(name = "Book")
@Table(name = "book")
public class BookJpaEntity {
    @Id
    @Column(name = "id", nullable = false, length = 32)
    private String id;

    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookStatus status;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    private AuthorJpaEntity author;

    public BookJpaEntity() {
    }

    private BookJpaEntity(String id, String title, String description, BookStatus status, Instant createdAt, AuthorJpaEntity author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.author = author;
    }

    public static BookJpaEntity from(Book book) {
        var author = new AuthorJpaEntity();
        author.setId(book.getAuthorID().getValue());
        return new BookJpaEntity(
                book.getId().getValue(),
                book.getTitle(),
                book.getDescription(),
                book.getStatus(),
                book.getCreatedAt(),
                author
        );
    }

    public Book toBook() {
        return Book.with(
                BookID.from(getId()),
                getTitle(),
                getDescription(),
                getStatus(),
                getCreatedAt(),
                AuthorID.from(getAuthor().getId())
        );
    }

    public AuthorJpaEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorJpaEntity author) {
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}