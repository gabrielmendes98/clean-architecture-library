package com.mylibrary.domain.book;

import com.mylibrary.domain.pagination.Pagination;
import com.mylibrary.domain.pagination.SearchQuery;

public interface BookGateway {
    Book create(Book book);

    Pagination<Book> findAll(SearchQuery query);

    Book findById(BookID id);

    Book update(Book book);
}
