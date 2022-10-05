package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

public class AdminResource {
    private static AdminResource adminResource;
    static CustomerService customerService =CustomerService.getInstance();
    ReservationService reservationService =ReservationService.getInstance();

    public static AdminResource getInstance() {
        if (adminResource == null){
            adminResource = new AdminResource();
        }
        return adminResource;
    }

    public Customer getCustomer(String email){
        return customerService.getCustomer(email);

    }
    public void addRoom(List<IRoom>rooms){
        rooms.forEach(reservationService::addRoom);
    }
    public static Collection<Customer>getAllCustomers(){
        return customerService.getAllCustomers();
    }
    public void displayAllReservations(){
        reservationService.printAllReservation();
    }

    public Collection<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }
}
