package com.api.logic.authentication;

import com.api.dalcomponent.interfaces.IUserRepository;
import com.api.dalcomponent.model.User;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.util.UUID;

public class RegisterHandler {

    private IUserRepository<User> userRepo;
    private PasswordHasher hasher;

    private static final Logger logger = Logger.getLogger(RegisterHandler.class);

    @Inject
    public RegisterHandler(IUserRepository<User> userRepo) {
        this.userRepo = userRepo;
        hasher = new PasswordHasher();
    }

    public void registerUser(String username, String password, String email) throws Exception {
        User user = new User();
        String hashedPassword = null;

        try {
            hashedPassword = hasher.generateStrongPassword(password);
        } catch (Exception exc) {
            logger.error(exc);
            throw exc;
        }
        user.setClientid(UUID.randomUUID().toString());
        user.setUsername(username);
        user.setPassword(hashedPassword);
        user.setEmail(email);

        if(!userRepo.save(user))
        {
            throw new Exception("Error registrating user");
        }
    }
}
