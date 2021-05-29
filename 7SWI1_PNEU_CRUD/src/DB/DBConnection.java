package DB;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection(){
        Connection connection=null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/demo","root","");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
