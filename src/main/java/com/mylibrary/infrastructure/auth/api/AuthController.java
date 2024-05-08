package com.mylibrary.infrastructure.auth.api;

import com.mylibrary.application.auth.authenticate.AuthenticateInput;
import com.mylibrary.application.auth.authenticate.AuthenticateOutput;
import com.mylibrary.application.auth.authenticate.AuthenticateUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthAPI {
    private final AuthenticateUseCase authenticateUseCase;

    public AuthController(AuthenticateUseCase authenticateUseCase) {
        this.authenticateUseCase = authenticateUseCase;
    }

    @Override
    public ResponseEntity<AuthenticateOutput> authenticate(AuthenticateInput input) {
        var output = authenticateUseCase.execute(input);

        if (output == null) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(new AuthenticateOutput(output.token()));
    }
}
