package com.mylibrary.infrastructure.configuration.usecases;

import com.mylibrary.application.user.admin.create.AdminCreateUserUseCase;
import com.mylibrary.application.user.create.CreateUserUseCase;
import com.mylibrary.domain.user.UserGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserUseCaseConfig {
    private final UserGateway userGateway;

    public UserUseCaseConfig(UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Bean
    public CreateUserUseCase createUserUseCase() {
        return new CreateUserUseCase(userGateway);
    }

    @Bean
    public AdminCreateUserUseCase adminCreateUserUseCase() {
        return new AdminCreateUserUseCase(userGateway);
    }
}
