package com.mylibrary.infrastructure.author.api;

import com.mylibrary.application.author.create.CreateAuthorInput;
import com.mylibrary.application.author.create.CreateAuthorOutput;
import com.mylibrary.domain.validation.handler.Notification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.vavr.control.Either;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("author")
@Tag(name = "Autores")
public interface AuthorAPI {
    @Operation(summary = "Cria um novo autor")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Autor criado com sucesso"),
            @ApiResponse(responseCode = "422", description = "Erro de validacao"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    Either<Notification, CreateAuthorOutput> createAuthor(@RequestBody CreateAuthorInput input);
}
