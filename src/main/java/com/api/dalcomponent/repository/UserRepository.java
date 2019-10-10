package com.api.dalcomponent.repository;
import com.api.dalcomponent.interfaces.IUserRepository;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

public class UserRepository<User> extends Repository<User> implements IUserRepository<User> {
    public UserRepository(Class<User> clazz) {
        super(clazz);
    }
}

