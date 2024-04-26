package com.mylibrary.infrastructure.user.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserJpaEntity, String> {
}
