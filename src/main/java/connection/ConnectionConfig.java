package connection;
import org.flywaydb.core.Flyway;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfig {

    public static Connection getConnection() {
        String DATABASE_URL = System.getenv("DATABASE_URL");
        String USER = System.getenv("USER");
        String PASSWORD = System.getenv("PASSWORD");

        Connection connection = null;

        try {
            Class.forName("org.postgresql.Driver");

            connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
            System.out.println("Connexion établie avec succès !");
            Flyway flyway = Flyway.configure()
                    .dataSource(DATABASE_URL, USER, PASSWORD)
                    .locations("classpath:db/migration/V1__initial_tables.sql")
                    .load();

            flyway.migrate();
        } catch (ClassNotFoundException e) {
            System.out.println("Driver PostgreSQL JDBC non trouvé : " + e.getMessage());
        } catch (SQLException e) {
            System.out.println("Erreur de connexion à la base de données : " + e.getMessage());
        }

        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }
}
