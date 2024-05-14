package com.mylibrary.application.rent.get;

import com.mylibrary.domain.rent.Rent;

import java.time.Instant;

public record GetRentOutput(
        String rentId,
        String bookId,
        String userId,
        Instant rentDate,
        Instant returnDate,
        boolean wasReturned
) {
    public static GetRentOutput from(Rent rent) {
        return new GetRentOutput(
                rent.getId().getValue(),
                rent.getBookID().getValue(),
                rent.getUserID().getValue(),
                rent.getRentDate(),
                rent.getReturnDate(),
                rent.wasReturned()
        );
    }
}
