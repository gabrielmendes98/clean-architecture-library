package com.mylibrary.infrastructure.configuration.usecases;

import com.mylibrary.application.book.create.CreateBookUseCase;
import com.mylibrary.application.book.get.GetBookUseCase;
import com.mylibrary.application.book.list.ListBooksUseCase;
import com.mylibrary.domain.book.BookGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BookUseCaseConfig {
    private final BookGateway bookGateway;

    public BookUseCaseConfig(BookGateway bookGateway) {
        this.bookGateway = bookGateway;
    }

    @Bean
    public CreateBookUseCase createBookUseCase() {
        return new CreateBookUseCase(bookGateway);
    }

    @Bean
    public ListBooksUseCase listBooksUseCase() {
        return new ListBooksUseCase(bookGateway);
    }

    @Bean
    public GetBookUseCase getBookUseCase() {
        return new GetBookUseCase(bookGateway);
    }
}
