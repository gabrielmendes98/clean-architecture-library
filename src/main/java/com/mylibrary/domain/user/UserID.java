package com.mylibrary.domain.user;

import com.mylibrary.domain.utils.IdUtils;
import com.mylibrary.domain.valueobjects.Identifier;

import java.util.Objects;

public class UserID extends Identifier {
    private final String value;

    private UserID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static UserID unique() {
        return UserID.from(IdUtils.uuid());
    }

    public static UserID from(final String id) {
        return new UserID(id);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final UserID that = (UserID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
