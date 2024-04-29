package com.mylibrary.infrastructure.rent.api;

import com.mylibrary.application.rent.create.CreateRentInput;
import com.mylibrary.application.rent.create.CreateRentOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("v1/rent")
@Tag(name = "Rent")
public interface RentAPI {
    @Operation(summary = "Rent a book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book rented successfully"),
            @ApiResponse(responseCode = "422", description = "Validation error"),
    })
    @PostMapping
    ResponseEntity<CreateRentOutput> rentBook(@RequestBody CreateRentInput input);
}
