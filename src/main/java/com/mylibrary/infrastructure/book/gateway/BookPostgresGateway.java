package com.mylibrary.infrastructure.book.gateway;

import com.mylibrary.domain.book.Book;
import com.mylibrary.domain.book.BookGateway;
import com.mylibrary.infrastructure.book.persistence.BookJpaEntity;
import com.mylibrary.infrastructure.book.persistence.BookRepository;
import org.springframework.stereotype.Component;

@Component
public class BookPostgresGateway implements BookGateway {
    private final BookRepository bookRepository;

    public BookPostgresGateway(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book create(Book book) {
        return bookRepository.save(BookJpaEntity.from(book)).toBook();
    }
}
