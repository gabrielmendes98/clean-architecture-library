package com.mylibrary.infrastructure.auth.api;

import com.mylibrary.application.auth.authenticate.AuthenticateInput;
import com.mylibrary.application.auth.authenticate.AuthenticateOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("v1/auth")
@Tag(name = "Authentication")
public interface AuthAPI {
    @Operation(summary = "Authenticate user or attendant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authenticated"),
            @ApiResponse(responseCode = "422", description = "Validation error"),
    })
    @PostMapping
    ResponseEntity<AuthenticateOutput> authenticate(@RequestBody AuthenticateInput input);
}
