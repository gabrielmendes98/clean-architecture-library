package com.mylibrary.infrastructure.user.api;

import com.mylibrary.application.user.admin.create.AdminCreateUserInput;
import com.mylibrary.application.user.admin.create.AdminCreateUserOutput;
import com.mylibrary.application.user.admin.create.AdminCreateUserUseCase;
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
    private final AdminCreateUserUseCase adminCreateUserUseCase;

    public UserController(final CreateUserUseCase createUserUseCase, final AdminCreateUserUseCase adminCreateUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.adminCreateUserUseCase = adminCreateUserUseCase;
    }

    @Override
    public ResponseEntity<CreateUserOutput> createUser(CreateUserInput input) {
        var output = createUserUseCase.execute(input);
        if (output.isLeft()) {
            throw new NotificationException("Error creating user", output.getLeft());
        }
        return ResponseEntity.created(URI.create("/user/" + output.get().id())).body(output.get());
    }

    @Override
    public ResponseEntity<AdminCreateUserOutput> adminCreateUser(AdminCreateUserInput input) {
        var output = adminCreateUserUseCase.execute(input);
        if (output.isLeft()) {
            throw new NotificationException("Error creating user", output.getLeft());
        }
        return ResponseEntity.created(URI.create("/user/" + output.get().id())).body(output.get());
    }
}
