package com.mylibrary.infrastructure.book.gateway;

import com.mylibrary.domain.book.Book;
import com.mylibrary.domain.book.BookGateway;
import com.mylibrary.domain.book.BookID;
import com.mylibrary.domain.pagination.Pagination;
import com.mylibrary.domain.pagination.SearchQuery;
import com.mylibrary.infrastructure.book.persistence.BookJpaEntity;
import com.mylibrary.infrastructure.book.persistence.BookRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.mylibrary.infrastructure.utils.SpecificationUtils.like;

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

    @Override
    public Pagination<Book> findAll(SearchQuery query) {
        final var page = PageRequest.of(
                query.page(),
                5
        );

        final var specifications = Optional.ofNullable(query.search())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult =
                this.bookRepository.findAll(Specification.where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(BookJpaEntity::toBook).toList()
        );
    }

    @Override
    public Book findById(BookID id) {
        return bookRepository.findById(id.getValue()).map(BookJpaEntity::toBook).orElse(null);
    }

    @Override
    public Book update(Book book) {
        return bookRepository.save(BookJpaEntity.from(book)).toBook();
    }


    private Specification<BookJpaEntity> assembleSpecification(final String str) {
        final Specification<BookJpaEntity> titleLike = like("title", str);
        final Specification<BookJpaEntity> idLike = like("id", str);
        return titleLike.or(idLike);
    }
}
