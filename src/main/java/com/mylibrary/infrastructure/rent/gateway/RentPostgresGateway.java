package com.mylibrary.infrastructure.rent.gateway;

import com.mylibrary.domain.EventPublisher;
import com.mylibrary.domain.rent.Rent;
import com.mylibrary.domain.rent.RentCreatedEvent;
import com.mylibrary.domain.rent.RentGateway;
import com.mylibrary.infrastructure.rent.persistence.RentJpaEntity;
import com.mylibrary.infrastructure.rent.persistence.RentRepository;
import org.springframework.stereotype.Component;

@Component
public class RentPostgresGateway implements RentGateway {
    private final RentRepository rentRepository;

    private final EventPublisher eventPublisher;

    public RentPostgresGateway(RentRepository rentRepository, EventPublisher eventPublisher) {
        this.rentRepository = rentRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Rent create(Rent rent) {
        var createdRent = rentRepository.save(RentJpaEntity.from(rent)).toRent();
        eventPublisher.send(new RentCreatedEvent(rent.getId().getValue(), rent.getBookID().getValue()));
        return createdRent;
    }

    @Override
    public Rent findById(String id) {
        var rent = rentRepository.findById(id);
        return rent.map(RentJpaEntity::toRent).orElse(null);
    }


}
