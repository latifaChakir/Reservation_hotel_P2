import java.sql.Connection;
import java.sql.SQLException;
import connection.ConnectionConfig;
import service.ChambreService;
import service.ClientService;
import service.HotelService;
import service.PrixDynamiqueService;
import ui.ReservationMenu;

public class Main {
    public static void main(String[] args) throws SQLException {
        ClientService clientService = new ClientService();
        ChambreService chambreService = new ChambreService();
        HotelService hotelService = new HotelService();
        PrixDynamiqueService prixDynamiqueService = new PrixDynamiqueService();
        ReservationMenu reservationMenu=new ReservationMenu(clientService,chambreService,hotelService,prixDynamiqueService);
        reservationMenu.reservationMenu();




    }
}
