import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import bean.Chambre;
import bean.Client;
import connection.ConnectionConfig;
import service.ChambreService;
import service.ClientService;
import ui.ClientMenu;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionConfig.getInstance().getConnection();

        ClientService clientService = new ClientService();
        ClientMenu clientMenu=new ClientMenu(clientService);
        clientMenu.clientMenu();




    }
}
