package com.mylibrary.application.author.create;

import com.mylibrary.domain.author.Author;

import java.time.Instant;

public record CreateAuthorOutput(
        String id,
        String name,
        Instant createdAt
) {
    public static CreateAuthorOutput from(final Author author) {
        return new CreateAuthorOutput(
                author.getId().getValue(),
                author.getName(),
                author.getCreatedAt()
        );
    }
}
