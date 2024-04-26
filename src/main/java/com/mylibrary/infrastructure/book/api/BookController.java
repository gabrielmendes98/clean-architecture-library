package com.mylibrary.infrastructure.book.api;

import com.mylibrary.application.book.create.CreateBookInput;
import com.mylibrary.application.book.create.CreateBookUseCase;
import com.mylibrary.domain.exceptions.NotificationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class BookController implements BookAPI {
    private final CreateBookUseCase createBookUseCase;

    public BookController(CreateBookUseCase createBookUseCase) {
        this.createBookUseCase = createBookUseCase;
    }

    @Override
    public ResponseEntity<?> createBook(CreateBookInput input) {
        var output = createBookUseCase.execute(input);
        if (output.isLeft()) {
            throw new NotificationException("Error creating book", output.getLeft());
        }
        return ResponseEntity.created(URI.create("/book/" + output.get().id())).build();
    }
}
