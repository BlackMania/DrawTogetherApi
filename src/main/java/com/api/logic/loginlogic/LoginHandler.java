package com.api.logic.loginlogic;

import com.api.dalcomponent.interfaces.IUserRepository;
import com.api.dalcomponent.model.User;

import javax.inject.Inject;
import java.sql.SQLException;

public class LoginHandler {
    private PasswordHasher hasher;
    private IUserRepository<User> userRepo;

    @Inject
    public LoginHandler(IUserRepository<User> userRepo) {
        this.userRepo = userRepo;
        this.hasher = new PasswordHasher();
    }

    public boolean ValidateLoginAttempt(String username, String password)
    {
        String hashedPassword = hasher.generateHash(password);
        try {
            for(User user : userRepo.getAll())
            {
                if(user.getPassword().equals(hashedPassword) && user.getUsername().equals(username))
                {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
