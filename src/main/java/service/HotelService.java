package service;

import bean.Hotel;
import Dao.impl.HotelDaoImpl;

import java.util.Scanner;

public class HotelService {

    private HotelDaoImpl hotelDaoImpl;
    private Scanner scanner;
    public HotelService() {
        this.hotelDaoImpl = new HotelDaoImpl();
        this.scanner = new Scanner(System.in);
    }
    public void saveHotel(){
        System.out.println("Enter hotel name: ");
        String hotelName = scanner.nextLine();
        System.out.println("Enter hotel address: ");
        String hotelAddress = scanner.nextLine();
        System.out.println("Enter hotel phone number: ");
        String hotelPhoneNumber = scanner.nextLine();
        Hotel hotel = new Hotel(hotelName, hotelAddress, hotelPhoneNumber);
        hotelDaoImpl.saveHotel(hotel);
    }
    public void updateHotel(){
        System.out.println("Enter hotel ID to update: ");
        int hotelID = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter hotel name: ");
        String hotelName = scanner.nextLine();
        System.out.println("Enter hotel address: ");
        String hotelAddress = scanner.nextLine();
        System.out.println("Enter hotel phone number: ");
        String hotelPhoneNumber = scanner.nextLine();
        Hotel hotel=new Hotel(hotelName, hotelAddress, hotelPhoneNumber);
        hotelDaoImpl.updateHotel(hotel);
    }
    public void deleteHotel(){
        System.out.println("Enter hotel ID to delete: ");
        int hotelID = scanner.nextInt();
        scanner.nextLine();
        hotelDaoImpl.deleteHotel(hotelID);
    }
    public Hotel getHotelByID(){
        System.out.println("Enter hotel ID: ");
        int hotelId = scanner.nextInt();
        return hotelDaoImpl.getHotelById(hotelId);
    }
}
