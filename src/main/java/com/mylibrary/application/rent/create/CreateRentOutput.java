package com.mylibrary.application.rent.create;

import com.mylibrary.domain.rent.Rent;

import java.time.Instant;

public record CreateRentOutput(
        String rentId,
        String bookId,
        String userId,
        Instant rentDate,
        Instant returnDate,
        boolean wasReturned
) {
    public static CreateRentOutput from(Rent rent) {
        return new CreateRentOutput(
                rent.getId().getValue(),
                rent.getBookID().getValue(),
                rent.getUserID().getValue(),
                rent.getRentDate(),
                rent.getReturnDate(),
                rent.wasReturned()
        );
    }
}
