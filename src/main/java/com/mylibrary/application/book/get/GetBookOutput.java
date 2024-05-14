package com.mylibrary.application.book.get;

import com.mylibrary.domain.book.Book;
import com.mylibrary.domain.book.BookStatus;

import java.time.Instant;

public record GetBookOutput(
        String id,
        String title,
        BookStatus status,
        Instant returnDate
) {
    public static GetBookOutput from(Book book) {
        return new GetBookOutput(
                book.getId().getValue(),
                book.getTitle(),
                book.getStatus(),
                book.getReturnDate()
        );
    }
}
