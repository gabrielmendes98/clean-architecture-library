package com.mylibrary.infrastructure.author.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<AuthorJpaEntity, String> {
}
