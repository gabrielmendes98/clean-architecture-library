package com.mylibrary.infrastructure.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController implements AuthAPI {
    private final AuthenticationService authenticationService;
    private final TokenService tokenService;

    public AuthController(AuthenticationService authenticationService, TokenService tokenService) {
        this.authenticationService = authenticationService;
        this.tokenService = tokenService;
    }

    @Override
    public ResponseEntity<AuthenticateOutput> authenticate(AuthenticateInput input) {
        var user = authenticationService.findUserByDocument(input.document());
        var isValid = authenticationService.validateUserPassword(user, input.password());

        if (!isValid) {
            return ResponseEntity.badRequest().build();
        }
        var token = tokenService.generateToken(user.toUser());
        return ResponseEntity.ok(new AuthenticateOutput(token));
    }
}
