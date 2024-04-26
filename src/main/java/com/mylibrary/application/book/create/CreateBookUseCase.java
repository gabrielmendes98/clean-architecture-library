package com.mylibrary.application.book.create;

import com.mylibrary.application.UseCase;
import com.mylibrary.domain.author.AuthorID;
import com.mylibrary.domain.book.Book;
import com.mylibrary.domain.book.BookGateway;
import com.mylibrary.domain.validation.handler.Notification;
import io.vavr.control.Either;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class CreateBookUseCase extends UseCase<CreateBookInput, Either<Notification, CreateBookOutput>> {
    private final BookGateway bookGateway;

    public CreateBookUseCase(BookGateway bookGateway) {
        this.bookGateway = bookGateway;
    }

    public Either<Notification, CreateBookOutput> execute(CreateBookInput input) {
        final var notification = Notification.create();
        final var book = Book.create(
                input.title(),
                input.description(),
                AuthorID.from(input.authorID())
        );
        book.validate(notification);
        return notification.hasError() ?
                Left(notification) :
                Try(() -> this.bookGateway.create(book))
                        .toEither()
                        .bimap(Notification::create, CreateBookOutput::from);
    }
}
