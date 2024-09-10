package Dao.impl;

import bean.Chambre;
import bean.Client;
import bean.Reservation;
import connection.ConnectionConfig;
import Dao.dao.ReservationDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDaoImpl extends ReservationDao {
    private ClientDaoImpl clientDaoImpl;
    private ChambreDaoImpl chambreDaoImpl;

    public ReservationDaoImpl() {
        clientDaoImpl = new ClientDaoImpl();
        chambreDaoImpl = new ChambreDaoImpl();
    }
    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String sql = "select * from reservations";
        try(Connection conn=ConnectionConfig.getInstance().getConnection();
        PreparedStatement ps=conn.prepareStatement(sql);){
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Reservation reservation=new Reservation();
                int reservationId=rs.getInt("id");
                int clientId=rs.getInt("id_client");
                int chambreId=rs.getInt("id_chambre");
                reservation.setDateDebut(rs.getDate("date_debut").toLocalDate());
                reservation.setDateFin(rs.getDate("date_fin").toLocalDate());
                Chambre chambre=chambreDaoImpl.getChambreById(chambreId);
                Client client=clientDaoImpl.getClientById(clientId);
                reservation.setClient(client);
                reservation.setChambre(chambre);
                reservation.setId(reservationId);
                reservations.add(reservation);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservations;
    }

    @Override
    public Reservation getReservationById(int reservationId) {
        Reservation reservation = null;
        String sql = "select * from reservations where id=?";
        try (Connection conn=ConnectionConfig.getInstance().getConnection();
        PreparedStatement ps=conn.prepareStatement(sql)) {
            ps.setInt(1, reservationId);
            try(ResultSet rs=ps.executeQuery();) {
                if(rs.next()) {
                    int clientID = rs.getInt("id_client");
                    int roomID = rs.getInt("id_chambre");
                    reservation.setDateDebut(rs.getDate("date_debut").toLocalDate());
                    reservation.setDateFin(rs.getDate("date_fin").toLocalDate());
                    Chambre chambre=chambreDaoImpl.getChambreById(roomID);
                    Client client=clientDaoImpl.getClientById(clientID);
                    reservation.setChambre(chambre);
                    reservation.setClient(client);
                }

            }catch (SQLException e) {
                e.printStackTrace();
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservation;
    }

    @Override
    public Reservation saveReservation(Reservation reservation) {
        String sql="INSERT INTO reservations(id_client,id_chambre,date_debut,date_fin) VALUES(?,?,?,?)";
        try(Connection conn= ConnectionConfig.getInstance().getConnection();
        PreparedStatement ps=conn.prepareStatement(sql)){
            Date dateDebut=Date.valueOf(reservation.getDateDebut());
            Date dateFin=Date.valueOf(reservation.getDateFin());
            ps.setLong(1,reservation.getClient().getId());
            ps.setLong(2,reservation.getChambre().getId());
            ps.setDate(3,dateDebut);
            ps.setDate(4,dateFin);
            ps.executeUpdate();

            System.out.println("Reservation added successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reservation;
    }

    @Override
    public void updateReservation(Reservation reservation) {
        String sql="update reservations set id_client=?, id_chambre=?, date_debut=?, date_fin=? where id=?";
        try(Connection conn=ConnectionConfig.getInstance().getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)
        ){
            Date dateDebut=Date.valueOf(reservation.getDateDebut());
            Date dateFin=Date.valueOf(reservation.getDateFin());
            ps.setLong(1,reservation.getClient().getId());
            ps.setLong(2,reservation.getChambre().getId());
            ps.setDate(3,dateDebut);
            ps.setDate(4,dateFin);
            ps.setInt(5,reservation.getId());
            ps.executeUpdate();
            System.out.println("Rservation updated successfully");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteReservation(int reservationId) {
        String sql="delete from reservations where id=?";
        try(Connection conn=ConnectionConfig.getInstance().getConnection();
        PreparedStatement ps=conn.prepareStatement(sql)){
            ps.setInt(1,reservationId);
            ps.executeUpdate();
            System.out.println("Reservation successfully deleted");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
