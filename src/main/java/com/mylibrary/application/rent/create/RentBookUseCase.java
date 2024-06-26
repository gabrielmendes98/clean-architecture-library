package com.mylibrary.application.rent.create;

import com.mylibrary.application.UseCase;
import com.mylibrary.domain.book.BookGateway;
import com.mylibrary.domain.book.BookID;
import com.mylibrary.domain.exceptions.DomainException;
import com.mylibrary.domain.rent.Rent;
import com.mylibrary.domain.rent.RentGateway;
import com.mylibrary.domain.user.UserID;
import com.mylibrary.domain.validation.Error;

public class RentBookUseCase extends UseCase<RentBookInput, RentBookOutput> {
    private final RentGateway rentGateway;
    private final BookGateway bookGateway;

    public RentBookUseCase(RentGateway rentGateway, BookGateway bookGateway) {
        this.rentGateway = rentGateway;
        this.bookGateway = bookGateway;
    }

    @Override
    public RentBookOutput execute(RentBookInput rentBookInput) {
        var book = this.bookGateway.findById(BookID.from(rentBookInput.bookId()));
        if (!book.isAvailable()) {
            throw DomainException.with(new Error("Book unavailable"));
        }
        var rent = Rent.create(
                BookID.from(rentBookInput.bookId()),
                UserID.from(rentBookInput.userId())
        );
        this.rentGateway.create(rent);
        return RentBookOutput.from(rent);
    }
}
