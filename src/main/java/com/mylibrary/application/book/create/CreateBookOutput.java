package com.mylibrary.application.book.create;

import com.mylibrary.domain.book.Book;

public record CreateBookOutput(
        String id
) {
    public static CreateBookOutput from(Book book) {
        return new CreateBookOutput(book.getId().getValue());
    }
}
