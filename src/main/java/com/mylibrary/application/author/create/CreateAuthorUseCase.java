package com.mylibrary.application.author.create;

import com.mylibrary.application.UseCase;
import com.mylibrary.domain.author.Author;
import com.mylibrary.domain.author.AuthorGateway;
import com.mylibrary.domain.validation.handler.Notification;
import com.mylibrary.domain.valueobjects.PersonName;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class CreateAuthorUseCase extends UseCase<CreateAuthorInput, Either<Notification, CreateAuthorOutput>> {
    private final AuthorGateway authorGateway;

    public CreateAuthorUseCase(AuthorGateway authorGateway) {
        this.authorGateway = Objects.requireNonNull(authorGateway);
    }

    @Override
    public Either<Notification, CreateAuthorOutput> execute(CreateAuthorInput input) {
        final var notification = Notification.create();
        final var author = Author.create(PersonName.of(input.name()));
        author.validate(notification);
        return notification.hasError() ? Left(notification) :
                Try(() -> this.authorGateway.create(author))
                        .toEither()
                        .bimap(Notification::create, CreateAuthorOutput::from);
    }
}
