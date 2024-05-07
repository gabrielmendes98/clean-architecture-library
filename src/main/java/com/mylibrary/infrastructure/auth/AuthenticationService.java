package com.mylibrary.infrastructure.auth;

import com.mylibrary.domain.valueobjects.password.Password;
import com.mylibrary.infrastructure.user.persistence.UserJpaEntity;
import com.mylibrary.infrastructure.user.persistence.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserJpaEntity findUserByDocument(String document) {
        return userRepository.findByDocument(document);
    }

    public boolean validateUserPassword(UserJpaEntity user, String password) {
        return Password.verifyPassword(password, user.getPassword(), user.getSalt());
    }
}
