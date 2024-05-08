package com.mylibrary.application.auth.authenticate;

import com.mylibrary.application.UseCase;
import com.mylibrary.domain.auth.TokenService;
import com.mylibrary.domain.user.User;
import com.mylibrary.domain.user.UserGateway;
import com.mylibrary.domain.valueobjects.password.Password;

public class AuthenticateUseCase extends UseCase<AuthenticateInput, AuthenticateOutput> {
    private final UserGateway userGateway;
    private final TokenService tokenService;

    public AuthenticateUseCase(UserGateway userGateway, TokenService tokenService) {
        this.userGateway = userGateway;
        this.tokenService = tokenService;
    }

    @Override
    public AuthenticateOutput execute(AuthenticateInput input) {
        var user = userGateway.findByDocument(input.document());
        var isValid = this.validateUserPassword(user, input.password());

        if (!isValid) {
            return null;
        }
        var token = tokenService.generateToken(user.getDocument());
        return new AuthenticateOutput(token);
    }

    private boolean validateUserPassword(User user, String password) {
        return Password.verifyPassword(password, user.getPassword().getValue(), user.getPassword().getSalt());
    }
}
