package service;

import model.Customer;
import model.IRoom;
import model.Reservation;

import java.util.*;
import java.util.concurrent.Callable;

public class ReservationService {
    private static ReservationService getInstance = new ReservationService();


    public Map<String, IRoom> rooms = new HashMap<>();
    private Map<String, Collection<Reservation>> reservations = new HashMap<>();

    public static ReservationService getInstance() {
        return getInstance;
    }

    public void addRoom(IRoom room) {
        rooms.put(room.getRoomNumber(), room);

    }

    public IRoom getARoom(String roomID) {
        return rooms.get(roomID);
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        Collection<Reservation> customerReservation = getCustomerReservation(customer);

        reservations.put(customer.getEmail(), customerReservation);
        return reservation;
    }
    public Collection<Reservation> getCustomerReservation(Customer customer) {
        return reservations.get(customer.getEmail());
    }

    public Collection<IRoom> findRooms (Date checkInDate, Date checkOutDate) {
        return findAllAvailablerooms(checkInDate, checkOutDate);
    }

    private Collection<IRoom> findAllAvailablerooms(Date checkInDate, Date checkOutDate) {
        findAllAvailablerooms(checkInDate, checkOutDate);
        return null;
    }
    public void printAllReservation(){
        System.out.println(reservations);
    }

    public Collection<IRoom> getAllRooms() {
        return rooms.values();
    }

    String date = "mm/dd/yyyy";

    }


