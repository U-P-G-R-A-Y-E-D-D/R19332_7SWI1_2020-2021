import DB.DBConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DBConnectionTest {

    @Test
    public void getConnection(){
        System.out.println("getConnection");
        Connection result = DBConnection.getConnection();
        assertEquals(result != null, true);
    }
}