package com.mylibrary.domain.rent;

public interface RentGateway {
    Rent create(Rent rent);

    Rent findById(String id);
}
