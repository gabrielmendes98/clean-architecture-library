package com.mylibrary.application.rent.create;

import com.mylibrary.application.UseCase;
import com.mylibrary.domain.book.BookGateway;
import com.mylibrary.domain.book.BookID;
import com.mylibrary.domain.exceptions.DomainException;
import com.mylibrary.domain.rent.Rent;
import com.mylibrary.domain.rent.RentGateway;
import com.mylibrary.domain.user.UserID;
import com.mylibrary.domain.validation.Error;

public class CreateRentUseCase extends UseCase<CreateRentInput, CreateRentOutput> {
    private final RentGateway rentGateway;
    private final BookGateway bookGateway;

    public CreateRentUseCase(RentGateway rentGateway, BookGateway bookGateway) {
        this.rentGateway = rentGateway;
        this.bookGateway = bookGateway;
    }

    @Override
    public CreateRentOutput execute(CreateRentInput createRentInput) {
        var book = this.bookGateway.findById(BookID.from(createRentInput.bookId()));
        if (!book.isAvailable()) {
            throw DomainException.with(new Error("Book unavailable"));
        }
        var rent = Rent.create(
                BookID.from(createRentInput.bookId()),
                UserID.from(createRentInput.userId())
        );
        this.rentGateway.create(rent);
        return CreateRentOutput.from(rent);
    }
}
