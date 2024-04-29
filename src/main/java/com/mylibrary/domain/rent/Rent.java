package com.mylibrary.domain.rent;

import com.mylibrary.domain.Entity;
import com.mylibrary.domain.book.BookID;
import com.mylibrary.domain.user.UserID;
import com.mylibrary.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

public class Rent extends Entity<RentID> {
    private final BookID bookID;
    private final UserID userID;
    private final Instant rentDate;
    private final Instant returnDate;
    private final Instant returnedDate;

    private Rent(RentID rentID, BookID bookID, UserID userID, Instant rentDate, Instant returnDate, Instant returnedDate) {
        super(rentID);
        this.bookID = Objects.requireNonNull(bookID, "'bookID' should not be null");
        this.userID = Objects.requireNonNull(userID, "'userID' should not be null");
        this.rentDate = Objects.requireNonNull(rentDate, "'rentDate' should not be null");
        this.returnDate = Objects.requireNonNull(returnDate, "'returnDate' should not be null");
        this.returnedDate = returnedDate;
    }

    public static Rent create(BookID bookID, UserID userID) {
        final var id = RentID.unique();
        final var rentDate = Instant.now();
        final var returnDate = calculateReturnDate();
        return new Rent(id, bookID, userID, rentDate, returnDate, null);
    }

    public static Rent with(RentID id, BookID bookID, UserID userID, Instant rentDate, Instant returnDate, Instant returnedDate) {
        return new Rent(id, bookID, userID, rentDate, returnDate, returnedDate);
    }

    private static Instant calculateReturnDate() {
        var DAYS_TO_RETURN = 7;
        return Instant.now().plusSeconds(60 * 60 * 24 * DAYS_TO_RETURN);
    }

    @Override
    public void validate(ValidationHandler handler) {
        throw new UnsupportedOperationException();
    }

    public boolean wasReturned() {
        return this.returnedDate != null;
    }

    public BookID getBookID() {
        return bookID;
    }

    public UserID getUserID() {
        return userID;
    }

    public Instant getRentDate() {
        return rentDate;
    }

    public Instant getReturnDate() {
        return returnDate;
    }

    public Instant getReturnedDate() {
        return returnedDate;
    }
}
