package util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MongoDBUtilTest {
    @Test
    public void testGetDatabaseNotNull() {
        assertNotNull(MongoDBUtil.getDatabase());
    }
}
