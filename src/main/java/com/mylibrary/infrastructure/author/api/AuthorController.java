package com.mylibrary.infrastructure.author.api;

import com.mylibrary.application.author.create.CreateAuthorInput;
import com.mylibrary.application.author.create.CreateAuthorOutput;
import com.mylibrary.application.author.create.CreateAuthorUseCase;
import com.mylibrary.domain.exceptions.NotificationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;

@RestController
public class AuthorController implements AuthorAPI {

    private final CreateAuthorUseCase createAuthorUseCase;

    public AuthorController(CreateAuthorUseCase createAuthorUseCase) {
        this.createAuthorUseCase = Objects.requireNonNull(createAuthorUseCase);
    }

    @Override
    public ResponseEntity<CreateAuthorOutput> createAuthor(CreateAuthorInput input) {
        var output = createAuthorUseCase.execute(input);
        if (output.isLeft()) {
            throw new NotificationException("Error creating author", output.getLeft());
        }
        return ResponseEntity.created(URI.create("/author/" + output.get().id())).body(output.get());
    }
}
