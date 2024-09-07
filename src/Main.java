import java.sql.Connection;
import connection.ConnectionConfig;
public class Main {
    public static void main(String[] args) {
        Connection connection = ConnectionConfig.getConnection();

    }
}
