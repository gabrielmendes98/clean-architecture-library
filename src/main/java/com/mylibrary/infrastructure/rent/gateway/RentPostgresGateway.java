package com.mylibrary.infrastructure.rent.gateway;

import com.mylibrary.domain.rent.Rent;
import com.mylibrary.domain.rent.RentGateway;
import com.mylibrary.infrastructure.rent.persistence.RentJpaEntity;
import com.mylibrary.infrastructure.rent.persistence.RentRepository;
import org.springframework.stereotype.Component;

@Component
public class RentPostgresGateway implements RentGateway {
    private final RentRepository rentRepository;

    public RentPostgresGateway(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
    }

    @Override
    public Rent create(Rent rent) {
        return rentRepository.save(RentJpaEntity.from(rent)).toRent();
    }
}
