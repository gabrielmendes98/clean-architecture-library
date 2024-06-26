package com.mylibrary.domain.author;

import com.mylibrary.domain.utils.IdUtils;
import com.mylibrary.domain.valueobjects.Identifier;

import java.util.Objects;

public class AuthorID extends Identifier {
    private final String value;

    private AuthorID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static AuthorID unique() {
        return AuthorID.from(IdUtils.uuid());
    }

    public static AuthorID from(final String id) {
        return new AuthorID(id);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final AuthorID that = (AuthorID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
