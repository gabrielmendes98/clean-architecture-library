package com.mylibrary.infrastructure.configuration.usecases;

import com.mylibrary.application.rent.create.CreateRentUseCase;
import com.mylibrary.application.rent.get.GetRentUseCase;
import com.mylibrary.domain.rent.RentGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentUseCaseConfig {
    private final RentGateway rentGateway;

    public RentUseCaseConfig(RentGateway rentGateway) {
        this.rentGateway = rentGateway;
    }

    @Bean
    public CreateRentUseCase createRentUseCase() {
        return new CreateRentUseCase(rentGateway);
    }

    @Bean
    public GetRentUseCase getRentUseCase() {
        return new GetRentUseCase(rentGateway);
    }
}
