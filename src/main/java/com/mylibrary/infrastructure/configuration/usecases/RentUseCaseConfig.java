package com.mylibrary.infrastructure.configuration.usecases;

import com.mylibrary.application.rent.create.RentBookUseCase;
import com.mylibrary.application.rent.get.GetRentUseCase;
import com.mylibrary.domain.book.BookGateway;
import com.mylibrary.domain.rent.RentGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RentUseCaseConfig {
    private final RentGateway rentGateway;
    private final BookGateway bookGateway;

    public RentUseCaseConfig(RentGateway rentGateway, BookGateway bookGateway) {
        this.rentGateway = rentGateway;
        this.bookGateway = bookGateway;
    }

    @Bean
    public RentBookUseCase rentBookUseCase() {
        return new RentBookUseCase(rentGateway, bookGateway);
    }

    @Bean
    public GetRentUseCase getRentUseCase() {
        return new GetRentUseCase(rentGateway);
    }
}
