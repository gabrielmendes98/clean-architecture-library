package com.mylibrary.infrastructure.rent.api;

import com.mylibrary.application.rent.create.RentBookInput;
import com.mylibrary.application.rent.create.RentBookOutput;
import com.mylibrary.application.rent.create.RentBookUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class RentController implements RentAPI {
    private final RentBookUseCase rentBookUseCase;

    public RentController(RentBookUseCase rentBookUseCase) {
        this.rentBookUseCase = rentBookUseCase;
    }

    @Override
    public ResponseEntity<RentBookOutput> rentBook(RentBookInput input) {
        var output = rentBookUseCase.execute(input);
        return ResponseEntity.created(URI.create("/rent/" + output.rentId())).body(output);
    }
}
