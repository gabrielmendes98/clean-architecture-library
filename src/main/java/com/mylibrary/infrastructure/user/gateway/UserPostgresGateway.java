package com.mylibrary.infrastructure.user.gateway;

import com.mylibrary.domain.user.User;
import com.mylibrary.domain.user.UserGateway;
import com.mylibrary.infrastructure.user.persistence.UserJpaEntity;
import com.mylibrary.infrastructure.user.persistence.UserRepository;
import org.springframework.stereotype.Component;

@Component
public class UserPostgresGateway implements UserGateway {

    private final UserRepository userRepository;

    public UserPostgresGateway(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(UserJpaEntity.from(user)).toUser();
    }
    
}
