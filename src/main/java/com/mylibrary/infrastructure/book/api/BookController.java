package com.mylibrary.infrastructure.book.api;

import com.mylibrary.application.book.create.CreateBookInput;
import com.mylibrary.application.book.create.CreateBookUseCase;
import com.mylibrary.application.book.list.ListBooksOutput;
import com.mylibrary.application.book.list.ListBooksUseCase;
import com.mylibrary.domain.exceptions.NotificationException;
import com.mylibrary.domain.pagination.Pagination;
import com.mylibrary.domain.pagination.SearchQuery;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class BookController implements BookAPI {
    private final CreateBookUseCase createBookUseCase;
    private final ListBooksUseCase listBooksUseCase;

    public BookController(CreateBookUseCase createBookUseCase, ListBooksUseCase listBooksUseCase) {
        this.createBookUseCase = createBookUseCase;
        this.listBooksUseCase = listBooksUseCase;
    }

    @Override
    public ResponseEntity<?> createBook(CreateBookInput input) {
        var output = createBookUseCase.execute(input);
        if (output.isLeft()) {
            throw new NotificationException("Error creating book", output.getLeft());
        }
        return ResponseEntity.created(URI.create("/book/" + output.get().id())).build();
    }

    @Override
    public Pagination<ListBooksOutput> listBooks(
            final String search,
            final int page
    ) {
        return listBooksUseCase.execute(new SearchQuery(page, search));
    }
}
