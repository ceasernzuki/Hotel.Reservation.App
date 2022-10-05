
import api.HotelResource;
import model.IRoom;
import model.Reservation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MainMenu {
    static HotelResource hotelResource = HotelResource.getInstance();

    private static void findAndReserveARoom() throws ParseException {
        Scanner scanner = new Scanner(System.in);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm/dd/yyyy");
        scanner.nextLine();

        System.out.println("Enter CheckIn Date mm/dd/yyyy example 01/01/2022");
        Date checkIn = getInputDate(scanner);
        System.out.println("Enter checkOut Date mm/dd/yyyy example 01/01/2022");
        Date checkOut = getInputDate(scanner);
        try {
            System.out.println("Enter CheckIn Date mm/dd/yyyy example 01/01/2022");

            Date checkInDate = new SimpleDateFormat("mm/dd/yyyy").parse(scanner.nextLine());
            System.out.println("mm/dd/yyyy");

            Date checkOutDate = new SimpleDateFormat("mm/dd/yyyy").parse(scanner.nextLine());
            System.out.println("mm/dd/yyyy");

            if (!checkInDate.before(checkInDate)) {
                Collection<IRoom> rooms = hotelResource.findARoom(checkInDate, checkOutDate);
                System.out.println("CheckIn has to be today's date or futures");

            } else {
                System.out.println("What room would you like? ");

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static Date getInputDate(Scanner scanner) throws ParseException {
        String format = "mm/dd/yyyy";
        try {
            return new SimpleDateFormat(format).parse(scanner.nextLine());
        } catch (ParseException ex) {
            System.out.println("Date is not valid");
            findAndReserveARoom();
        }
        return null;
    }

    private static void seeMyReservations() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter your Email");
        String email = scanner.nextLine();
        Collection<Reservation> reservations = hotelResource.getCustomersReservations(email);
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    private void showAdminMenu() throws ParseException {
        AdminMenu adminMenu = new AdminMenu();
        adminMenu.start();
    }

    private static void createAnAccount() {
        Scanner scanner = new Scanner(System.in);
        Boolean validEmail = false;

        System.out.println("Please enter your email ");
        System.out.println("Thank you !");

        System.out.println("Your first Name");
        String fn = scanner.nextLine();


        System.out.println("Your last Name");
        String ln = scanner.nextLine();

        hotelResource.createACustomer(" ", " ", " ");
        String email = " ";


        while (validEmail) {
            System.out.println("Enter Email format: name@domain.com: ");
            email = scanner.nextLine();
            String emailRegex = "^ (.+) @ (.+).com ";

            Pattern pattern = Pattern.compile(emailRegex);
            if (pattern.matcher(email).matches()) {
                validEmail = true;
            } else System.out.println("invalid");
        }
    }


    public static void start() {
        Scanner scanner = new Scanner(System.in);
        int choice;
        boolean quit = false;

        do {

            displayMenu();

            choice = scanner.nextInt();

            try {
                switch (choice) {

                    case '1':
                        findAndReserveARoom();
                        break;

                    case '2':
                        seeMyReservations();
                        break;
                    case '3':
                        createAnAccount();
                        break;
                    case '4':
                        new AdminMenu();
                        break;
                    case '5':
                        System.out.println("Quit");
                        break;


                }

            } catch (Exception ex) {
                System.out.println("Please choose between 1 - 5");
                scanner.next();
            }

        } while (quit);
    }
    public static void displayMenu() {
        System.out.println("\n Welcome to the Hotel Reservation Application ");
        System.out.println(" ___________________________________________________\n");
        System.out.println("1." + "Find and Reserve a Room\n ");
        System.out.println("2." + "See my Reservations\n ");
        System.out.println("3." + "Create an Account \n ");
        System.out.println("4." + "Admin \n ");
        System.out.println("5." + "Exit \n ");
        System.out.println("Please select one of the options\n ");
    }
}