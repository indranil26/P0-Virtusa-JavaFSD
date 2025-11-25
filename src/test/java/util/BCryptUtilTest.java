package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BCryptUtilTest {

    @Test
    public void testHashAndCheck() {
        String password = "secure123";
        String hash = BCryptUtil.hash(password);
        assertTrue(BCryptUtil.check(password, hash));
        assertFalse(BCryptUtil.check("wrongpass", hash));
    }
}
