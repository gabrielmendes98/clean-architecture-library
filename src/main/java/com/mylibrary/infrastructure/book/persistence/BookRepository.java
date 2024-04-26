package com.mylibrary.infrastructure.book.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookJpaEntity, String> {
}
