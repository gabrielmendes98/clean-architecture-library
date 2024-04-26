package com.mylibrary.domain.valueobjects.password;

import com.mylibrary.domain.validation.Error;
import com.mylibrary.domain.validation.ValidationHandler;
import com.mylibrary.domain.validation.Validator;

import java.util.Arrays;

public class PasswordValidator extends Validator {
    private static final int MIN_PASSWORD_LENGTH = 8;
    private final Password password;
    private final char[] plainPassword;

    public PasswordValidator(final Password password, final String plainPassword, final ValidationHandler handler) {
        super(handler);
        this.password = password;
        this.plainPassword = plainPassword.toCharArray();
    }

    @Override
    public void validate() {
        final var hashedPassword = this.password.getValue();
        // Check for null password
        if (hashedPassword == null || plainPassword == null || plainPassword.length < MIN_PASSWORD_LENGTH) {
            this.validationHandler().append(new Error("'password' should has at least 8 characters"));
            return;
        }

        // Check for at least one uppercase letter, one lowercase letter, one digit, and one special character
        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;
        for (char ch : plainPassword) {
            if (Character.isUpperCase(ch)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(ch)) {
                hasLowercase = true;
            } else if (Character.isDigit(ch)) {
                hasDigit = true;
            } else {
                hasSpecial = true;
            }
        }
        if (!hasUppercase || !hasLowercase || !hasDigit || !hasSpecial) {
            this.validationHandler().append(new Error("'password' should has at least one uppercase letter, one lowercase letter, one digit, and one special character"));
        }
        Arrays.fill(plainPassword, ' ');
    }
}
