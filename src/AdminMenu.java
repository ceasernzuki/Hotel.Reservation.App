
import api.AdminResource;
import api.HotelResource;
import model.*;
import java.util.Collection;
import java.util.List;

import java.util.Scanner;

public class AdminMenu {

    private static List<IRoom> room;
    Scanner scanner = new Scanner(System.in);
    static AdminResource adminResource = AdminResource.getInstance();
    private static RoomType roomType;
    HotelResource hotelResource = HotelResource.getInstance();

    public AdminMenu() {
        super();
    }

    public static void displayMenu() {
        System.out.println("Admin Menu");
        System.out.println("------------------------------\n ");
        System.out.println("1." + "See all Customers \n ");
        System.out.println("2." + "See all Rooms\n ");
        System.out.println("3." + "See all Reservations\n ");
        System.out.println("4." + "Add a Room\n ");
        System.out.println("5." + "Main Menu\n ");
        System.out.println("please choose one of the choices\n ");
    }

    public static void seeAllCustomers() {
        Collection<Customer> customers = adminResource.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No Customers at the moment");
            for (Customer customer : customers) {
                System.out.println(customer.toString());
            }
        } else {
            System.out.println("No customers as of now");
        }
    }

    public static void seeAllRooms() {
        Collection<IRoom> rooms = adminResource.getAllRooms();

        if (rooms.isEmpty()) {
            System.out.println("No room available");
        } else {
            adminResource.getAllRooms().forEach(System.out::println);
        }
    }

    public static void seeAllReservations() {
        adminResource.displayAllReservations();
    }

    public static void addARoom(List<IRoom> room) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter room number ");
        String roomNumber = scanner.nextLine();

        System.out.println("Enter price per night");
        Double roomPrice = RoomPrice(scanner);

        System.out.println("enter room type: 1 - Single bed, 2- Double bed ");
        int choice;
        choice = scanner.nextInt();
        if (choice == 1) {
            roomType = RoomType.SINGLE;
        } else System.out.println("Invalid entry");
        while (choice != 1 && choice != 2) ;
        room(roomNumber, roomPrice, roomType);
        adminResource.addRoom(room);
        scanner.nextLine();

        System.out.println("Please add another room? Y or N ?");
        addARoom(room);


    }

    private static void room(String roomNumber, Double roomPrice, RoomType roomType) {

    }

    private static RoomType chooseRoomType(Scanner scanner) {
        try {
            return RoomType.valueOf(scanner.nextLine());
        } catch (IllegalArgumentException exception) {
            System.out.println("Room price is not valid! please re enter!");
        }
        return chooseRoomType(scanner);
    }

    private static Double RoomPrice(Scanner scanner) {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException exp) {
            System.out.println("Room is invalid " + "please enter a valid room");
        }
        return RoomPrice(scanner);
    }

    private static void addTestData() {
        IRoom room1 = new Room("101", 100.0, RoomType.SINGLE);
        IRoom room2 = new Room("201", 200.0, RoomType.DOUBLE);
        try {
            adminResource.addRoom((List<IRoom>) room1);
            adminResource.addRoom((List<IRoom>) room2);
        } catch (Exception e) {
        }
    }


    public static void start() {
        String choice;
        boolean quit = false;
        Scanner scanner = new Scanner(System.in);
        do {
            displayMenu();
            choice = scanner.nextLine();

            try {
                switch (choice) {

                    case "1":
                        seeAllCustomers();
                        break;
                    case "2":
                        seeAllRooms();
                        break;
                    case "3":
                        seeAllReservations();
                        break;
                    case "4":
                        addARoom(room);
                        break;
                    case "5":
                        addTestData();
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value: " + choice);
                }
            } catch (Exception exception) {
                System.out.println("Invalid entry");
                scanner.nextLine();
            }
        }
        while (!quit);
    }
}