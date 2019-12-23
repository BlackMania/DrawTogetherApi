package com.api.logic.authentication;

import com.api.dalcomponent.interfaces.IUserRepository;
import com.api.dalcomponent.model.User;

import javax.inject.Inject;
import java.util.UUID;

public class RegisterHandler {

    private IUserRepository<User> userRepo;
    private PasswordHasher hasher;

    @Inject
    public RegisterHandler(IUserRepository<User> userRepo) {
        this.userRepo = userRepo;
        hasher = new PasswordHasher();
    }

    public void registerUser(String username, String password, String email) throws Exception
    {
        User user = new User();
        String hashedPassword = null;

        try{
            hashedPassword = hasher.generateStrongPassword(password);
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
            throw exc;
        }
        user.setClientid(UUID.randomUUID().toString());
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setEmail(email);

        try{
            userRepo.save(user);
        } catch (Exception exc)
        {
            exc.printStackTrace();
            throw exc;
        }
    }
}
