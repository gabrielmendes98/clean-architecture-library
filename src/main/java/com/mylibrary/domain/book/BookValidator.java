package com.mylibrary.domain.book;

import com.mylibrary.domain.validation.Error;
import com.mylibrary.domain.validation.ValidationHandler;
import com.mylibrary.domain.validation.Validator;

public class BookValidator extends Validator {
    private final Book book;

    public BookValidator(Book book, ValidationHandler handler) {
        super(handler);
        this.book = book;
    }

    @Override
    public void validate() {
        if (book.getTitle() == null) {
            this.validationHandler().append(new Error("'title' must not be null"));
        }

        if (book.getAuthorID() == null) {
            this.validationHandler().append(new Error("'authorID' must not be null"));
        }

        if (book.getStatus() == null) {
            this.validationHandler().append(new Error("'status' must not be null"));
        }
    }
}
