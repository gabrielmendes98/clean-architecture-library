package com.mylibrary.application.user.admin.create;

import com.mylibrary.application.UseCase;
import com.mylibrary.domain.user.User;
import com.mylibrary.domain.user.UserGateway;
import com.mylibrary.domain.validation.handler.Notification;
import com.mylibrary.domain.valueobjects.PersonName;
import com.mylibrary.domain.valueobjects.password.Password;
import io.vavr.control.Either;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

public class AdminCreateUserUseCase extends UseCase<AdminCreateUserInput, Either<Notification, AdminCreateUserOutput>> {
    private final UserGateway userGateway;

    public AdminCreateUserUseCase(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Override
    public Either<Notification, AdminCreateUserOutput> execute(AdminCreateUserInput input) {
        final var notification = Notification.create();
        final var plainPassword = Password.generateRandomPassword();
        final var password = Password.create(plainPassword);
        final var user = User.create(
                PersonName.from(input.name()),
                input.document(),
                password,
                input.role()
        );
        user.validate(notification);
        return notification.hasError() ? Left(notification) :
                Try(() -> this.userGateway.create(user))
                        .toEither()
                        .bimap(Notification::create, u -> new AdminCreateUserOutput(plainPassword, user.getId().getValue()));
    }
}
