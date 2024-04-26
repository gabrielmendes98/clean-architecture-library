package com.mylibrary.application.book.create;

public record CreateBookInput(
        String title,
        String description,
        String authorID
) {
}
