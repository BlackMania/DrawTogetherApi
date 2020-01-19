package com.api.dalcomponent;

import com.api.dalcomponent.interfaces.IUserRepository;
import com.api.dalcomponent.model.User;
import com.api.dalcomponent.repository.UserRepository;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRepositoryTest {
    IUserRepository<User> userRepo;

    public UserRepositoryTest() {
        userRepo = new UserRepository<User>(InMemTableUtils.getContext());
    }


    @Test
    public void stage1_getAllUsersFromDatabase() {
        List<User> users = new ArrayList<>();

        users = userRepo.getAll();

        Assert.assertEquals("testuser", users.get(0).getUsername());
        Assert.assertEquals(1, users.size());
    }

    @Test
    public void stage2_getUserById() {
        User user = null;

        user = userRepo.findById(1);

        Assert.assertNotNull(user);
    }

    @Test
    public void stage3_getUserByClientId() {
        User user = null;

        user = userRepo.findByClientId("550e8400-e29b-41d4-a716-446655440000");

        Assert.assertNotNull(user);
    }

    @Test
    public void stage4_removeUserFromDB() {
        User user = new User();
        user.setClientid(UUID.randomUUID().toString());
        user.setUsername("test");
        user.setPassword("pass");

        userRepo.save(user);

        Assert.assertEquals(2, userRepo.getAll().size());

        userRepo.delete(user);

        Assert.assertEquals(1, userRepo.getAll().size());
    }

    @Test
    public void stage5_addUserToDb() {
        User user = new User();
        user.setClientid(UUID.randomUUID().toString());
        user.setUsername("test");
        user.setPassword("pass");

        userRepo.save(user);

        Assert.assertEquals(2, userRepo.getAll().size());
        userRepo.delete(user);
        Assert.assertEquals(1, userRepo.getAll().size());
    }
}
