package com.mylibrary.infrastructure.author.api;

import com.mylibrary.application.author.create.CreateAuthorInput;
import com.mylibrary.application.author.create.CreateAuthorOutput;
import com.mylibrary.application.author.create.CreateAuthorUseCase;
import com.mylibrary.domain.validation.handler.Notification;
import io.vavr.control.Either;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class AuthorController implements AuthorAPI {

    private final CreateAuthorUseCase createAuthorUseCase;

    public AuthorController(CreateAuthorUseCase createAuthorUseCase) {
        this.createAuthorUseCase = Objects.requireNonNull(createAuthorUseCase);
    }

    @Override
    public Either<Notification, CreateAuthorOutput> createAuthor(CreateAuthorInput input) {
        return createAuthorUseCase.execute(input);
    }
}
