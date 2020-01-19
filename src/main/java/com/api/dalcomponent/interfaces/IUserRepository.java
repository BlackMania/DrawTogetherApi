package com.api.dalcomponent.interfaces;

import com.api.dalcomponent.model.User;

public interface IUserRepository<T extends User> extends IRepository<User> {
    User findByClientId(String id);
}
