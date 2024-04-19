package com.mylibrary.domain.author;

import com.mylibrary.domain.validation.Error;
import com.mylibrary.domain.validation.ValidationHandler;
import com.mylibrary.domain.validation.Validator;

public class AuthorValidator extends Validator {
    public static final int NAME_MAX_LENGTH = 255;
    public static final int NAME_MIN_LENGTH = 3;
    private final Author author;

    public AuthorValidator(final Author author, final ValidationHandler handler) {
        super(handler);
        this.author = author;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.author.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final int length = name.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
        }
    }
}
