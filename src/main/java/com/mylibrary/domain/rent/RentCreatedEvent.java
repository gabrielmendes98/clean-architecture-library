package com.mylibrary.domain.rent;


import java.time.Instant;

public record RentCreatedEvent(String rentId, String bookId, Instant returnDate) {
}