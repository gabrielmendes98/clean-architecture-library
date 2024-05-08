package com.mylibrary.domain.user;

public interface UserGateway {
    User create(User user);

    User findByDocument(String document);
}
