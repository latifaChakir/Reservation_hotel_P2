package Dao.impl;
import Dao.dao.PrixDynamiqueDao;
import bean.PrixDynamique;
import enums.Saison;
import enums.Days;
import enums.Events;
import connection.ConnectionConfig;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrixDynamiqueDaoImpl extends PrixDynamiqueDao {

    public PrixDynamiqueDaoImpl() {
    }

    public PrixDynamique save(PrixDynamique prixDynamique) {
        String query = "INSERT INTO prix_dynamique (saison, day_of_week, event, coefficient) VALUES (?, ?, ?, ?)";
        try (Connection connection= ConnectionConfig.getInstance().getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, prixDynamique.getSeason().name());
            statement.setString(2, prixDynamique.getDayOfWeek().name());
            statement.setString(3, prixDynamique.getEvent() != null ? prixDynamique.getEvent().name() : null);
            statement.setBigDecimal(4, prixDynamique.getCoefficient());
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return prixDynamique;
    }

    @Override
    public void update(PrixDynamique pricedynamic) {

    }

    @Override
    public void delete(int pricedynamicId) {

    }

    public List<PrixDynamique> findAll()  {
        List<PrixDynamique> pricingRules = new ArrayList<>();
        String query = "SELECT * FROM prix_dynamique";
        try (Connection connection= ConnectionConfig.getInstance().getConnection();
                Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Saison saison = Saison.valueOf(resultSet.getString("saison"));
                Days dayOfWeek = Days.valueOf(resultSet.getString("day_of_week"));
                Events event = resultSet.getString("event") != null ? Events.valueOf(resultSet.getString("event")) : null;
                BigDecimal coefficient = resultSet.getBigDecimal("coefficient");
                PrixDynamique prixDynamique = new PrixDynamique(saison, dayOfWeek, event, coefficient);
                pricingRules.add(prixDynamique);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return pricingRules;
    }

    @Override
    public PrixDynamique findById(int pricedynamicId) {
        return null;
    }

}
