package com.mylibrary.application.book.list;

import com.mylibrary.application.UseCase;
import com.mylibrary.domain.book.BookGateway;
import com.mylibrary.domain.pagination.Pagination;
import com.mylibrary.domain.pagination.SearchQuery;

public class ListBooksUseCase extends UseCase<SearchQuery, Pagination<ListBooksOutput>> {
    private final BookGateway bookGateway;

    public ListBooksUseCase(BookGateway bookGateway) {
        this.bookGateway = bookGateway;
    }

    public Pagination<ListBooksOutput> execute(SearchQuery searchQuery) {
        return this.bookGateway.findAll(searchQuery)
                .map(ListBooksOutput::from);
    }
}
