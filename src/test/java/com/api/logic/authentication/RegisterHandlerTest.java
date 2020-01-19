package com.api.logic.authentication;

import com.api.dalcomponent.InMemTableUtils;
import com.api.dalcomponent.interfaces.IUserRepository;
import com.api.dalcomponent.model.User;
import com.api.dalcomponent.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class RegisterHandlerTest {
    private IUserRepository<User> userRepo;
    public RegisterHandlerTest() {
        userRepo = new UserRepository<User>(InMemTableUtils.getContext());
    }

    @Test
    public void registerUserTest() throws Exception {
        RegisterHandler handler = new RegisterHandler(userRepo);

        handler.registerUser("Vincent", "password1!", "testmail@mail.nl");

        Assert.assertEquals(2, userRepo.getAll().size());

        List<User> users = userRepo.getAll();
        userRepo.delete(users.get(1));
        Assert.assertEquals(1, userRepo.getAll().size());
    }

    @Test
    public void registerUserWithTooBigInput()
    {
        RegisterHandler handler = new RegisterHandler(userRepo);

        Assert.assertThrows(Exception.class, () -> {
            handler.registerUser("verybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusernameverybigusername", "password1", "test@mail.nl");
        });
    }
}
