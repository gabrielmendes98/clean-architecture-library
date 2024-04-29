package com.mylibrary.infrastructure.rent.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RentRepository extends JpaRepository<RentJpaEntity, String> {
}
