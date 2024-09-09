import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import bean.Chambre;
import bean.Client;
import connection.ConnectionConfig;
import service.ChambreService;
import service.ClientService;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionConfig.getInstance().getConnection();

        ChambreService chambreService = new ChambreService();

        // Récupérer toutes les chambres
        List<Chambre> chambres = chambreService.getAllChambres();
        chambres.forEach(System.out::println);

        ClientService clientService = new ClientService();

        // Récupérer toutes les chambres
        List<Client> clients = clientService.getAllClients();
        clients.forEach(System.out::println);


    }
}
