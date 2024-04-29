package com.mylibrary.domain.rent;

import com.mylibrary.domain.utils.IdUtils;
import com.mylibrary.domain.valueobjects.Identifier;

import java.util.Objects;

public class RentID extends Identifier {
    private final String value;

    private RentID(String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static RentID unique() {
        return RentID.from(IdUtils.uuid());
    }

    public static RentID from(String id) {
        return new RentID(id);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final RentID that = (RentID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
