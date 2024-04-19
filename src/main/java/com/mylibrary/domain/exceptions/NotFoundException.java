package com.mylibrary.domain.exceptions;

import com.mylibrary.domain.Entity;
import com.mylibrary.domain.Identifier;
import com.mylibrary.domain.validation.Error;

import java.util.Collections;
import java.util.List;

public class NotFoundException extends DomainException {

    protected NotFoundException(final String message, final List<Error> errors) {
        super(message, errors);
    }

    public static NotFoundException with(
            final Class<? extends Entity<?>> entity,
            final Identifier id
    ) {
        final var error = "%s with ID %s was not found".formatted(
                entity.getSimpleName(),
                id.getValue()
        );
        return new NotFoundException(error, Collections.emptyList());
    }

    public static NotFoundException with(final Error error) {
        return new NotFoundException(error.message(), List.of(error));
    }
}
