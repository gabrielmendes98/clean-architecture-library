package com.mylibrary.infrastructure.author.persistence;

import com.mylibrary.domain.author.Author;
import com.mylibrary.domain.author.AuthorID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

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
                author.getName(),
                author.getCreatedAt()
        );
    }

    public Author toAuthor() {
        return Author.with(
                AuthorID.from(getId()),
                getName(),
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