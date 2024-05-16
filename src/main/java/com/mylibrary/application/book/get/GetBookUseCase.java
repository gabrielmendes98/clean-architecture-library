package com.mylibrary.application.book.get;

import com.mylibrary.application.UseCase;
import com.mylibrary.domain.book.BookGateway;
import com.mylibrary.domain.book.BookID;

public class GetBookUseCase extends UseCase<GetBookInput, GetBookOutput> {
    private final BookGateway bookGateway;

    public GetBookUseCase(BookGateway bookGateway) {
        this.bookGateway = bookGateway;
    }

    @Override
    public GetBookOutput execute(GetBookInput input) {
        var rent = this.bookGateway.findById(BookID.from(input.bookId()));
        return GetBookOutput.from(rent);
    }
}
