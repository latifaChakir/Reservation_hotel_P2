import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import bean.Chambre;
import bean.Client;
import connection.ConnectionConfig;
import dao.ChambreDao;
import service.HotelService;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionConfig.getInstance().getConnection();

        ChambreDao chambreDao = new ChambreDao();
        List<Chambre> chambres = chambreDao.getAllChambres(); // passer la connexion
        System.out.println(chambres);

        HotelService hotelService = new HotelService();
        Client client = new Client( "John Doe",20,"dfgff","354678");

        client.setId(1);
        boolean reservationCree = hotelService.creerReservation(client, 1, LocalDate.now(), LocalDate.now().plusDays(5));
        if (reservationCree) {
            System.out.println("Réservation créée avec succès !");
        } else {
            System.out.println("Échec de la création de la réservation.");
        }
    }
}
