package com.mylibrary.domain.user;

import com.mylibrary.domain.validation.Error;
import com.mylibrary.domain.validation.ValidationHandler;
import com.mylibrary.domain.validation.Validator;

public class UserValidator extends Validator {
    private final User user;

    public UserValidator(final User user, final ValidationHandler handler) {
        super(handler);
        this.user = user;
    }

    @Override
    public void validate() {
        user.getName().validate(this.validationHandler());

        var document = user.getDocument();
        if (document == null) {
            this.validationHandler().append(new Error("'document' must not be null"));
        } else if (document.length() != 11) {
            this.validationHandler().append(new Error("'document' must have 11 characters"));
        }

        if (user.getRole() == null) {
            this.validationHandler().append(new Error("'role' must not be null"));
        }
    }
}
