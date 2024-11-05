import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SQLConnection {

    private static Connection conn;


    public static Connection getConn(){
        if (conn == null){
            try {
                conn = DriverManager.getConnection(
                        "jdbc:mariadb://localhost:3306/employee_local?user=user&password=pw"
                );
            } catch (SQLException e){
                e.printStackTrace();
            }
            return conn;
        }
        else {
            return conn;
        }
    }

}
