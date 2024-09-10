import java.sql.Connection;
import java.sql.SQLException;
import connection.ConnectionConfig;
import service.ChambreService;
import service.ClientService;
import service.HotelService;
import ui.ReservationMenu;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionConfig.getInstance().getConnection();

        ClientService clientService = new ClientService();
        ChambreService chambreService = new ChambreService();
        HotelService hotelService = new HotelService();
        ReservationMenu reservationMenu=new ReservationMenu(clientService,chambreService,hotelService);
        reservationMenu.reservationMenu();




    }
}
