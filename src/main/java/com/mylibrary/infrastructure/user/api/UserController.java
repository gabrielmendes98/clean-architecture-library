package com.mylibrary.infrastructure.user.api;

import com.mylibrary.application.user.create.CreateUserInput;
import com.mylibrary.application.user.create.CreateUserOutput;
import com.mylibrary.application.user.create.CreateUserUseCase;
import com.mylibrary.domain.exceptions.NotificationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class UserController implements UserAPI {

    private final CreateUserUseCase createUserUseCase;

    public UserController(final CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @Override
    public ResponseEntity<CreateUserOutput> createUser(CreateUserInput input) {
        var output = createUserUseCase.execute(input);
        if (output.isLeft()) {
            throw new NotificationException("Error creating user", output.getLeft());
        }
        return ResponseEntity.created(URI.create("/user/" + output.get().token())).body(output.get());
    }
}
