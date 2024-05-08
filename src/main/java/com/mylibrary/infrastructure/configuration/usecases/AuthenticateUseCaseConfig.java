package com.mylibrary.infrastructure.configuration.usecases;

import com.mylibrary.application.auth.authenticate.AuthenticateUseCase;
import com.mylibrary.domain.auth.TokenService;
import com.mylibrary.domain.user.UserGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthenticateUseCaseConfig {
    private final UserGateway userGateway;
    private final TokenService tokenService;

    public AuthenticateUseCaseConfig(UserGateway userGateway, TokenService tokenService) {
        this.userGateway = userGateway;
        this.tokenService = tokenService;
    }

    @Bean
    public AuthenticateUseCase authenticateUseCase() {
        return new AuthenticateUseCase(userGateway, tokenService);
    }
}
