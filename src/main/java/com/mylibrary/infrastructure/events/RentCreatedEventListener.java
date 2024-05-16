package com.mylibrary.infrastructure.events;

import com.mylibrary.domain.book.BookGateway;
import com.mylibrary.domain.book.BookID;
import com.mylibrary.domain.rent.RentCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class RentCreatedEventListener {
    private final BookGateway bookGateway;

    public RentCreatedEventListener(BookGateway bookGateway) {
        this.bookGateway = bookGateway;
    }

    @EventListener
    public void onBookRent(@NonNull RentCreatedEvent event) {
        var book = this.bookGateway.findById(BookID.from(event.bookId()));
        if (book != null) {
            book.rentBook(event.returnDate());
            bookGateway.update(book);
        }
    }
}