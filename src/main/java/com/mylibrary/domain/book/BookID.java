package com.mylibrary.domain.book;

import com.mylibrary.domain.utils.IdUtils;
import com.mylibrary.domain.valueobjects.Identifier;

import java.util.Objects;

public class BookID extends Identifier {
    private final String value;

    private BookID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static BookID unique() {
        return BookID.from(IdUtils.uuid());
    }

    public static BookID from(final String id) {
        return new BookID(id);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BookID that = (BookID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
