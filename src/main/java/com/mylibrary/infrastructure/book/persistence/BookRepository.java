package com.mylibrary.infrastructure.book.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookJpaEntity, String> {

    Page<BookJpaEntity> findAll(Specification<BookJpaEntity> whereClause, Pageable page);


}
