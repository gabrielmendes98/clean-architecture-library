package com.mylibrary.application.user.create;

import com.mylibrary.application.UseCase;
import com.mylibrary.domain.user.User;
import com.mylibrary.domain.user.UserGateway;
import com.mylibrary.domain.user.UserRole;
import com.mylibrary.domain.validation.handler.Notification;
import com.mylibrary.domain.valueobjects.PersonName;
import com.mylibrary.domain.valueobjects.password.Password;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class CreateUserUseCase extends UseCase<CreateUserInput, Either<Notification, CreateUserOutput>> {
    private final UserGateway userGateway;

    public CreateUserUseCase(UserGateway userGateway) {
        this.userGateway = Objects.requireNonNull(userGateway);
    }

    @Override
    public Either<Notification, CreateUserOutput> execute(CreateUserInput input) {
        final var notification = Notification.create();
        final var password = Password.from(input.password());
        final var user = User.create(
                PersonName.from(input.name()),
                input.document(),
                password,
                UserRole.CLIENT
        );
        user.validate(notification);
        password.validate(input.password(), notification);
        // generate jwt token
        var fakeToken = "fakeToken";
        return notification.hasError() ? Left(notification) :
                Try(() -> this.userGateway.create(user))
                        .toEither()
                        .bimap(Notification::create, u -> new CreateUserOutput(fakeToken));
    }
}
