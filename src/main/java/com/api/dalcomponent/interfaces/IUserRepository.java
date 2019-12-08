package com.api.dalcomponent.interfaces;

import com.api.dalcomponent.model.User;

import java.sql.SQLException;

public interface IUserRepository<T extends User> extends IRepository<User> {
    public User findByClientId(String id) throws SQLException;
}
