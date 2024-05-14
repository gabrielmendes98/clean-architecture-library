package com.mylibrary.application.book.list;

import com.mylibrary.domain.book.Book;
import com.mylibrary.domain.book.BookStatus;

import java.time.Instant;

public record ListBooksOutput(
        String id,
        String title,
        BookStatus status,
        Instant returnDate
) {
    public static ListBooksOutput from(Book book) {
        return new ListBooksOutput(
                book.getId().getValue(),
                book.getTitle(),
                book.getStatus(),
                book.getReturnDate()
        );
    }
}
