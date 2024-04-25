package com.mylibrary.domain.valueobjects;

import com.mylibrary.domain.validation.Error;
import com.mylibrary.domain.validation.ValidationHandler;

public class PersonName extends ValueObject {
    public static final int NAME_MAX_LENGTH = 255;
    public static final int NAME_MIN_LENGTH = 3;
    private final String value;

    private PersonName(final String value) {
        this.value = value;
    }

    public static PersonName of(final String value) {
        return new PersonName(value);
    }

    public static PersonName from(final String name) {
        return new PersonName(name);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final PersonName that = (PersonName) o;
        return getValue().equals(that.getValue());
    }

    public void validate(ValidationHandler handler) {
        if (value == null) {
            handler.append(new Error("'name' should not be null"));
            return;
        }

        if (value.isBlank()) {
            handler.append(new Error("'name' should not be empty"));
            return;
        }

        final int length = value.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            handler.append(new Error("'name' must be between 3 and 255 characters"));
        }
    }

}
