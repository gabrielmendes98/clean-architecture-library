package com.mylibrary.application.rent.create;

import com.mylibrary.domain.rent.Rent;

import java.time.Instant;

public record RentBookOutput(
        String rentId,
        String bookId,
        String userId,
        Instant rentDate,
        Instant returnDate,
        boolean wasReturned
) {
    public static RentBookOutput from(Rent rent) {
        return new RentBookOutput(
                rent.getId().getValue(),
                rent.getBookID().getValue(),
                rent.getUserID().getValue(),
                rent.getRentDate(),
                rent.getReturnDate(),
                rent.wasReturned()
        );
    }
}
