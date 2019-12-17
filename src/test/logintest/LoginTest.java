package logintest;

import com.api.logic.loginlogic.PasswordHasher;
import org.junit.Assert;
import org.junit.Test;

public class LoginTest {

    private String password;

    public LoginTest() {
        PasswordHasher hasher = new PasswordHasher();
        password = hasher.generateHash("password");
    }

    @Test
    public void Login()
    {
        PasswordHasher hasher = new PasswordHasher();
        Boolean succes = hasher.validatePassword("password", password);

        Assert.assertEquals(true, succes);
    }
}
