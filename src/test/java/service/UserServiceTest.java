package service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    @Test
    public void testRegisterNewUser() throws Exception {
        UserService us = new UserService();
        boolean result = us.register("alice", "Alice Smith", "alice@ex.com", "pass123");
        assertTrue(result);
    }

    @Test
    public void testRegisterDuplicateUser() throws Exception {
        UserService us = new UserService();
        us.register("bob", "Bob Brown", "bob@ex.com", "pass456");
        boolean result = us.register("bob", "Bobby", "bobby@ex.com", "pass789");
        assertFalse(result);
    }

    @Test
    public void testLoginSuccessAndFail() throws Exception {
        UserService us = new UserService();
        us.register("eve", "Eve Adams", "eve@ex.com", "evesecret");
        assertNotNull(us.login("eve", "evesecret"));
        assertNull(us.login("eve", "wrongpw"));
    }
}
