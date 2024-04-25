package com.mylibrary.domain.user;

import com.mylibrary.domain.Entity;
import com.mylibrary.domain.validation.ValidationHandler;
import com.mylibrary.domain.valueobjects.PersonName;

public class User extends Entity<UserID> {
    private final PersonName name;

    private User(UserID id, PersonName name) {
        super(id);
        this.name = name;
    }

    @Override
    public void validate(ValidationHandler handler) {
        
    }
}
