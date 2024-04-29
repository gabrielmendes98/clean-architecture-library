package com.mylibrary.infrastructure.rent.persistence;

import com.mylibrary.domain.book.BookID;
import com.mylibrary.domain.rent.Rent;
import com.mylibrary.domain.rent.RentID;
import com.mylibrary.domain.user.UserID;
import com.mylibrary.infrastructure.book.persistence.BookJpaEntity;
import com.mylibrary.infrastructure.user.persistence.UserJpaEntity;
import jakarta.persistence.*;

import java.time.Instant;

@Entity(name = "Rent")
@Table(name = "rent")
public class RentJpaEntity {
    @Id
    @Column(name = "id", nullable = false, length = 32)
    private String id;

    @Column(name = "rent_date", nullable = false)
    private Instant rentDate;

    @Column(name = "return_date", nullable = false)
    private Instant returnDate;

    @Column(name = "returned_date")
    private Instant returnedDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id", nullable = false)
    private BookJpaEntity book;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserJpaEntity user;

    public RentJpaEntity() {
    }

    private RentJpaEntity(String id, Instant rentDate, Instant returnDate, Instant returnedDate, BookJpaEntity book, UserJpaEntity user) {
        this.id = id;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.returnedDate = returnedDate;
        this.book = book;
        this.user = user;
    }

    public static RentJpaEntity from(final Rent rent) {
        var bookJpaEntity = new BookJpaEntity();
        bookJpaEntity.setId(rent.getBookID().getValue());
        var userJpaEntity = new UserJpaEntity();
        userJpaEntity.setId(rent.getUserID().getValue());
        return new RentJpaEntity(
                rent.getId().getValue(),
                rent.getRentDate(),
                rent.getReturnDate(),
                rent.getReturnedDate(),
                bookJpaEntity,
                userJpaEntity
        );
    }


    public Rent toRent() {
        return Rent.with(
                RentID.from(getId()),
                BookID.from(getBook().getId()),
                UserID.from(getUser().getId()),
                getRentDate(),
                getReturnDate(),
                getReturnedDate()
        );
    }


    public UserJpaEntity getUser() {
        return user;
    }

    public void setUser(UserJpaEntity user) {
        this.user = user;
    }

    public BookJpaEntity getBook() {
        return book;
    }

    public void setBook(BookJpaEntity book) {
        this.book = book;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getRentDate() {
        return rentDate;
    }

    public void setRentDate(Instant rentDate) {
        this.rentDate = rentDate;
    }

    public Instant getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Instant returnDate) {
        this.returnDate = returnDate;
    }

    public Instant getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Instant returnedDate) {
        this.returnedDate = returnedDate;
    }
}
