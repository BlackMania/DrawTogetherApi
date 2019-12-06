package com.api.dalcomponent.repository;
import com.api.dalcomponent.interfaces.IUserRepository;
import com.api.dalcomponent.model.User;

import javax.inject.Inject;
public class UserRepository<T extends User> extends Repository<User> implements IUserRepository<User> {
    @Inject
    public UserRepository() {
        super(User.class);
    }
}

