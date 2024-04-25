package com.mylibrary.domain.author;

import com.mylibrary.domain.validation.ValidationHandler;
import com.mylibrary.domain.validation.Validator;

public class AuthorValidator extends Validator {
    private final Author author;

    public AuthorValidator(final Author author, final ValidationHandler handler) {
        super(handler);
        this.author = author;
    }

    @Override
    public void validate() {
        this.author.getName().validate(this.validationHandler());
    }
}
