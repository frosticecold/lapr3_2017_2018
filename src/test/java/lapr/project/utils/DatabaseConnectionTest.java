package lapr.project.utils;

import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseConnectionTest {
    
    public DatabaseConnectionTest() {
    }

    /**
     * Test of getDatabase method, of class DatabaseConnection.
     */
    @Test
    public void testGetDatabase() {
        System.out.println("getDatabase");
        DatabaseConnection instance = new DatabaseConnection();
        SQLConnection db = instance.getDatabase();
        assertTrue(db != null && db instanceof SQLConnection);
    }
    
}
