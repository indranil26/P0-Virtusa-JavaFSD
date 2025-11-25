package dao;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.User;

public class userDaoTest {

    @Test
    public void testInsertAndQueryUser() throws Exception {
        userDao ud = new userDao();
        ud.insertUser("john", "John Doe", "john@ex.com", "hashedpw");
        User user = ud.getByUsername("john");
        assertEquals("john", user.getUsername());
        assertEquals("John Doe", user.getFullName());
    }

    @Test
    public void testExistsByUsernameOrEmail() throws Exception {
        userDao ud = new userDao();
        ud.insertUser("jane", "Jane Doe", "jane@ex.com", "hashedpw");
        assertTrue(ud.existsByUsernameOrEmail("jane", "notused@ex.com"));
        assertTrue(ud.existsByUsernameOrEmail("notused", "jane@ex.com"));
        assertFalse(ud.existsByUsernameOrEmail("nouser", "noemail@ex.com"));
    }
}
