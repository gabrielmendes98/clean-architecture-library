package com.mylibrary.application.rent.create;

import com.mylibrary.application.UseCase;
import com.mylibrary.domain.book.BookID;
import com.mylibrary.domain.rent.Rent;
import com.mylibrary.domain.rent.RentGateway;
import com.mylibrary.domain.user.UserID;

public class CreateRentUseCase extends UseCase<CreateRentInput, CreateRentOutput> {
    private final RentGateway rentGateway;

    public CreateRentUseCase(RentGateway rentGateway) {
        this.rentGateway = rentGateway;
    }

    @Override
    public CreateRentOutput execute(CreateRentInput createRentInput) {
        var rent = Rent.create(
                BookID.from(createRentInput.bookId()),
                UserID.from(createRentInput.userId())
        );
        this.rentGateway.create(rent);
        return CreateRentOutput.from(rent);
    }
}
