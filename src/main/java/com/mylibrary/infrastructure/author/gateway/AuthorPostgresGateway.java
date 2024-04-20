package com.mylibrary.infrastructure.author.gateway;

import com.mylibrary.domain.author.Author;
import com.mylibrary.domain.author.AuthorGateway;
import com.mylibrary.infrastructure.author.persistence.AuthorJpaEntity;
import com.mylibrary.infrastructure.author.persistence.AuthorRepository;
import org.springframework.stereotype.Component;

@Component
public class AuthorPostgresGateway implements AuthorGateway {
    private final AuthorRepository authorRepository;

    public AuthorPostgresGateway(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author create(Author author) {
        return this.authorRepository.save(AuthorJpaEntity.from(author)).toAuthor();
    }
}
