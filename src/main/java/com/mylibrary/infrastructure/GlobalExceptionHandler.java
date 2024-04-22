package com.mylibrary.infrastructure;

import com.mylibrary.domain.exceptions.DomainException;
import com.mylibrary.domain.exceptions.NotFoundException;
import com.mylibrary.domain.exceptions.NotificationException;
import com.mylibrary.domain.validation.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(final NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError.from(ex));
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = DomainException.class)
    public ResponseEntity<ApiError> handleDomainException(final DomainException ex) {
        return ResponseEntity.unprocessableEntity().body(ApiError.from(ex));
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = NotificationException.class)
    public ResponseEntity<ApiError> handleNotificationException(final NotificationException ex) {
        return ResponseEntity.unprocessableEntity().body(ApiError.from(ex));
    }

    public record ApiError(String message, List<Error> errors) {
        static ApiError from(final DomainException ex) {
            return new ApiError(ex.getMessage(), ex.getErrors());
        }
    }
}