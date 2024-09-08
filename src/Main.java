import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import bean.Chambre;
import connection.ConnectionConfig;
import dao.ChambreDao;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = ConnectionConfig.getInstance().getConnection();

        ChambreDao chambreDao = new ChambreDao();
        List<Chambre> chambres = chambreDao.getAllChambres(); // passer la connexion
        System.out.println(chambres);
    }
}
