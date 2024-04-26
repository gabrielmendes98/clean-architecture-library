package com.mylibrary.infrastructure.user.api;

import com.mylibrary.application.user.create.CreateUserInput;
import com.mylibrary.application.user.create.CreateUserOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("v1/user")
@Tag(name = "User")
public interface UserAPI {
    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "422", description = "Validation error"),
    })
    @PostMapping
    ResponseEntity<CreateUserOutput> createUser(@RequestBody CreateUserInput input);
}
