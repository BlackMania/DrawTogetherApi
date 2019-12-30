package com.api.logic.authentication;

import com.api.dalcomponent.InMemTableUtils;
import com.api.dalcomponent.interfaces.IUserRepository;
import com.api.dalcomponent.model.User;
import com.api.dalcomponent.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;

public class RegisterHandlerTests {
    private IUserRepository<User> userRepo;
    public RegisterHandlerTests() {
        try {
            InMemTableUtils.generateInMemDatabase();
            userRepo = new UserRepository<User>(InMemTableUtils.getContext());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void registerUserTest() throws Exception {
        RegisterHandler handler = new RegisterHandler(userRepo);

        handler.registerUser("Vincent", "password1!", "testmail@mail.nl");

        Assert.assertEquals(2, userRepo.getAll().size());

    }
}
