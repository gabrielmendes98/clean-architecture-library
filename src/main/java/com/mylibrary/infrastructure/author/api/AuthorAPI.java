package com.mylibrary.infrastructure.author.api;

import com.mylibrary.application.author.create.CreateAuthorInput;
import com.mylibrary.application.author.create.CreateAuthorOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("v1/author")
@Tag(name = "Author")
public interface AuthorAPI {
    @Operation(summary = "Create a new author", security = {@SecurityRequirement(name = "bearer-key")})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Author created successfully"),
            @ApiResponse(responseCode = "422", description = "Validation error"),
    })
    @PostMapping
    ResponseEntity<CreateAuthorOutput> createAuthor(@RequestBody CreateAuthorInput input);
}
