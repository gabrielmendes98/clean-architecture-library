package com.mylibrary.infrastructure.rent.api;

import com.mylibrary.application.rent.create.CreateRentInput;
import com.mylibrary.application.rent.create.CreateRentOutput;
import com.mylibrary.application.rent.create.CreateRentUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class RentController implements RentAPI {
    private final CreateRentUseCase createRentUseCase;

    public RentController(CreateRentUseCase createRentUseCase) {
        this.createRentUseCase = createRentUseCase;
    }

    @Override
    public ResponseEntity<CreateRentOutput> rentBook(CreateRentInput input) {
        var output = createRentUseCase.execute(input);
        return ResponseEntity.created(URI.create("/rent/" + output.rentId())).body(output);
    }
}
