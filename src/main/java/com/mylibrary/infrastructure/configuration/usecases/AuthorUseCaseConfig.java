package com.mylibrary.infrastructure.configuration.usecases;

import com.mylibrary.application.author.create.CreateAuthorUseCase;
import com.mylibrary.domain.author.AuthorGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthorUseCaseConfig {
    private final AuthorGateway authorGateway;

    public AuthorUseCaseConfig(AuthorGateway authorGateway) {
        this.authorGateway = authorGateway;
    }

    @Bean
    public CreateAuthorUseCase createAuthorUseCase() {
        return new CreateAuthorUseCase(authorGateway);
    }
}
