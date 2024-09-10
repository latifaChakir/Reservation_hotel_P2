package repository.impl;

import bean.Hotel;
import connection.ConnectionConfig;
import repository.dao.HotelDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelDaoImpl extends HotelDao {

    @Override
    public List<Hotel> getAllHotels() {
        List hotels = new ArrayList();
        String sql = "select * from hotel";
        try (Connection conn = ConnectionConfig.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String hotelName = rs.getString("name");
                String hotelAddress = rs.getString("address");
                String hotelPhone = rs.getString("phone");
                hotels.add(new Hotel(hotelName, hotelAddress, hotelPhone));
            }
        } catch (SQLException sqlException) {
            System.out.println("Error fetching data hotels"+sqlException.getMessage());
        }
        return hotels;
    }
        @Override
    public Hotel getHotelById(int hotelId) {
        Hotel hotel = null;
        String sql = "select * from hotel where hotel_id=?";
        try(Connection conn=ConnectionConfig.getInstance().getConnection();
            PreparedStatement ps=conn.prepareStatement(sql)){
                ps.setInt(1, hotelId);
                ResultSet rs=ps.executeQuery();
                try {
                    if(rs.next()){
                        String hotelName = rs.getString("name");
                        String hotelAddress = rs.getString("address");
                        String hotelPhone = rs.getString("phone");
                        hotel=new Hotel(hotelName,hotelAddress,hotelPhone);
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return hotel;
    }

    @Override
    public Hotel saveHotel(Hotel hotel) {
        String query="INSERT INTO hotel(name,address,phone) VALUES(?,?,?,?,?)";
        try (Connection conn = ConnectionConfig.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, hotel.getName());
            ps.setString(2, hotel.getAddress());
            ps.setString(3, hotel.getPhone());
            ps.executeUpdate();
            System.out.println("Hotel saved successfully!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotel;
    }

    @Override
    public void updateHotel(Hotel hotel) {
        String query="UPDATE hotel SET name=?,address=?,phone=? WHERE id=?";
        try(Connection conn=ConnectionConfig.getInstance().getConnection();
            PreparedStatement ps = conn.prepareStatement(query)
        ){
            ps.setString(1, hotel.getName());
            ps.setString(2, hotel.getAddress());
            ps.setString(3, hotel.getPhone());
            ps.setInt(4, hotel.getId());
            ps.executeUpdate();
            System.out.println("hotel updated successfully!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteHotel(int hotelId) {
        String query="DELETE FROM hotel WHERE id=?";
        try (Connection conn=ConnectionConfig.getInstance().getConnection();
        PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1, hotelId);
            ps.executeUpdate();
            System.out.println("hotel deleted successfully!");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
