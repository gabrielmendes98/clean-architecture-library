package com.mylibrary.infrastructure.book.api;

import com.mylibrary.application.book.create.CreateBookInput;
import com.mylibrary.application.book.list.ListBooksOutput;
import com.mylibrary.domain.pagination.Pagination;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("v1/book")
@Tag(name = "Book")
public interface BookAPI {
    @Operation(summary = "Create a new book")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Book created successfully"),
            @ApiResponse(responseCode = "422", description = "Validation error"),
    })
    @PostMapping
    ResponseEntity<?> createBook(@RequestBody CreateBookInput input);

    @GetMapping
    Pagination<ListBooksOutput> listBooks(
            @RequestParam(name = "search", required = false, defaultValue = "") final String search,
            @RequestParam(name = "page", required = false, defaultValue = "0") final int page
    );
}
