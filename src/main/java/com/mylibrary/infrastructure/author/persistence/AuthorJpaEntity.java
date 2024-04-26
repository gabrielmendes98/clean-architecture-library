package com.mylibrary.infrastructure.author.persistence;

import com.mylibrary.domain.author.Author;
import com.mylibrary.domain.author.AuthorID;
import com.mylibrary.domain.valueobjects.PersonName;
import com.mylibrary.infrastructure.book.persistence.BookJpaEntity;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Author")
@Table(name = "author")
public class AuthorJpaEntity {
    @Id
    @Column(name = "id", nullable = false, length = 32)
    private String id;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "author", orphanRemoval = true)
    private List<BookJpaEntity> books = new ArrayList<>();

    public AuthorJpaEntity() {
    }

    private AuthorJpaEntity(String id, String name, Instant createdAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.name = name;
    }

    public static AuthorJpaEntity from(final Author author) {
        return new AuthorJpaEntity(
                author.getId().getValue(),
                author.getName().getValue(),
                author.getCreatedAt()
        );
    }

    public List<BookJpaEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookJpaEntity> books) {
        this.books = books;
    }

    public void addBook(BookJpaEntity book) {
        this.books.add(book);
    }

    public Author toAuthor() {
        return Author.with(
                AuthorID.from(getId()),
                PersonName.from(getName()),
                getCreatedAt()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}