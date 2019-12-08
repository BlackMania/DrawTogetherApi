package com.api.dalcomponent.repository;
import com.api.dalcomponent.interfaces.IUserRepository;
import com.api.dalcomponent.model.Game;
import com.api.dalcomponent.model.User;

import javax.inject.Inject;
import java.sql.SQLException;

public class UserRepository<T extends User> extends Repository<User> implements IUserRepository<User> {
    @Inject
    public UserRepository() {
        super(User.class);
    }

    public User findByClientId(String id) throws SQLException {
       User queriedUser = dao.queryForFirst(dao.queryBuilder().where().eq("clientid", id).prepare());

       return queriedUser;
    }
}

