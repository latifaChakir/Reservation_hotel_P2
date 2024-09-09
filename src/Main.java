import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import bean.Chambre;
import bean.Client;
import connection.ConnectionConfig;
import service.ChambreService;
import service.ClientService;
import ui.ChambreMenu;
import ui.ClientMenu;
import ui.ReservationMenu;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionConfig.getInstance().getConnection();

        ClientService clientService = new ClientService();
        ChambreService chambreService = new ChambreService();
        ReservationMenu reservationMenu=new ReservationMenu(clientService,chambreService);
        reservationMenu.reservationMenu();




    }
}
