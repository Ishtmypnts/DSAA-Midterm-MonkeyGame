import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// Membership class with buffet lounge benefit and fixed discount based on type
class Membership {
    private String membershipId;
    private String membershipType; // Silver, Gold, Platinum
    private int discountPercentage;
    private boolean buffetLounge;
    static final int consoleWidth = 120;

    public Membership(String membershipId, String membershipType, boolean buffetLounge) {
        this.membershipId = membershipId;
        this.membershipType = membershipType;
        this.buffetLounge = buffetLounge;
        this.discountPercentage = setDiscountPercentage(membershipType);
    }

    private int setDiscountPercentage(String membershipType) {
        switch (membershipType.toLowerCase()) {
            case "silver":
                return 10;
            case "gold":
                return 15;
            case "platinum":
                return 25;
            default:
                return 0; // no discount for unknown type
        }
    }

    public String getMembershipId() {
        return membershipId;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public boolean hasBuffetLounge() {
        return buffetLounge;
    }
}

class Room {
    private int roomNumber;
    private boolean isVIP;
    public boolean isBooked;
    public Customer bookedBy;
    private double price;

    public Room(int roomNumber, boolean isVIP) {
        this.roomNumber = roomNumber;
        this.isVIP = isVIP;
        this.isBooked = false;
        this.bookedBy = null;
        this.price = isVIP ? 4000 : 1500; // fixed prices as per request
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public boolean isVIP() {
        return isVIP;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public Customer getBookedBy() {
        return bookedBy;
    }

    public double getPrice() {
        return price;
    }

    public void book(Customer customer) {
        this.isBooked = customer != null;
        this.bookedBy = customer;
    }
}

class Customer {
    private String name;
    private int age;
    private String contactNumber;
    private String email;
    private String checkInTime;
    private String checkOutTime;
    private String date;
    private int numberOfPeople;
    private Membership membership;

    public Customer(String name, int age, String contactNumber, String email,
                    String checkInTime, String checkOutTime, String date, int numberOfPeople,
                    Membership membership) {
        this.name = name;
        this.age = age;
        this.contactNumber = contactNumber;
        this.email = email;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.date = date;
        this.numberOfPeople = numberOfPeople;
        this.membership = membership;
    }

    public String getName() {
        return name;
    }

    public Membership getMembership() {
        return membership;
    }

    public String getCheckInTime() {
        return checkInTime;
    }

    public String getCheckOutTime() {
        return checkOutTime;
    }
}

class Hotel {
    private Room[] rooms;
    private Queue<Customer> vipQueue;
    private Queue<Customer> standardQueue;

    public Hotel(int totalRooms, int vipRooms) {
        rooms = new Room[totalRooms];
        vipQueue = new LinkedList<>();
        standardQueue = new LinkedList<>();

        for (int i = 0; i < totalRooms; i++) {
            rooms[i] = new Room(i + 1, i < vipRooms);
        }
    }

    private double calculateAmount(Customer c, Room room) {
        double basePrice = room.getPrice();
        Membership m = c.getMembership();
        if (m != null) {
            double discount = basePrice * m.getDiscountPercentage() / 100.0;
            return basePrice - discount;
        }
        return basePrice;
    }

    public void bookRoom() {
        if (!vipQueue.isEmpty()) {
            Customer vipCustomer = vipQueue.poll();
            Room room = getAvailableRoom(true);
            if (room != null) {
                room.book(vipCustomer);
                double amount = calculateAmount(vipCustomer, room);
                System.out.print("Room " + room.getRoomNumber() + " booked for " + vipCustomer.getName() + " (VIP)");
                if (vipCustomer.getMembership() != null) {
                    System.out.print(" with " + vipCustomer.getMembership().getMembershipType() +
                            " membership (Discount: " + vipCustomer.getMembership().getDiscountPercentage() + "%)");
                    if (vipCustomer.getMembership().hasBuffetLounge()) {
                        System.out.print(" + buffet lounge access");
                    }
                    System.out.print(".");
                }
                System.out.printf(" Amount to be paid: %.2f%n", amount);
                return;
            }
        }

        if (!standardQueue.isEmpty()) {
            Customer standardCustomer = standardQueue.poll();
            Room room = getAvailableRoom(false);
            if (room != null) {
                room.book(standardCustomer);
                double amount = calculateAmount(standardCustomer, room);
                System.out.print("Room " + room.getRoomNumber() + " booked for " + standardCustomer.getName() + " (Standard)");
                if (standardCustomer.getMembership() != null) {
                    System.out.print(" with " + standardCustomer.getMembership().getMembershipType() +
                            " membership (Discount: " + standardCustomer.getMembership().getDiscountPercentage() + "%)");
                    if (standardCustomer.getMembership().hasBuffetLounge()) {
                        System.out.print(" + buffet lounge access");
                    }
                    System.out.print(".");
                }
                System.out.printf(" Amount to be paid: %.2f%n", amount);
                return;
            }
        }
        System.out.println("No rooms available for booking.");
    }

    public void addToQueue(Customer customer, boolean isVIP) {
        if (isVIP) {
            vipQueue.add(customer);
            System.out.println(customer.getName() + " added to VIP queue.");
        } else {
            standardQueue.add(customer);
            System.out.println(customer.getName() + " added to Standard queue.");
        }
    }

    private Room getAvailableRoom(boolean isVIP) {
        for (Room room : rooms) {
            if (room.isVIP() == isVIP && !room.isBooked()) {
                return room;
            }
        }
        return null;
    }

    public void viewRooms() {
        System.out.println("\nRoom Status:");
        for (Room room : rooms) {
            String status = room.isBooked() ? "Booked" : "Available";
            String type = room.isVIP() ? "VIP" : "Standard";
            System.out.printf("Room %2d - %-9s - %s - Price: %.2f%n", room.getRoomNumber(), type, status, room.getPrice());
        }
    }

    public void viewQueues() {
        System.out.println("\nVIP Queue:");
        if (vipQueue.isEmpty()) {
            System.out.println("No VIP customers in queue.");
        } else {
            for (Customer c : vipQueue) {
                System.out.println("- " + c.getName());
            }
        }
        System.out.println("\nStandard Queue:");
        if (standardQueue.isEmpty()) {
            System.out.println("No standard customers in queue.");
        } else {
            for (Customer c : standardQueue) {
                System.out.println("- " + c.getName());
            }
        }
    }

    public void viewBookedRooms() {
        System.out.println("\nBooked Room Details:");
        boolean found = false;
        for (Room room : rooms) {
            if (room.isBooked()) {
                Customer c = room.getBookedBy();
                double amount = calculateAmount(c, room);
                System.out.printf("Room %2d (%s) - Booked by: %s, Check-in: %s, Check-out: %s",
                        room.getRoomNumber(),
                        room.isVIP() ? "VIP" : "Standard",
                        c.getName(),
                        c.getCheckInTime(),
                        c.getCheckOutTime());
                if (c.getMembership() != null) {
                    System.out.printf(" [Membership: %s (Discount: %d%%)", c.getMembership().getMembershipType(), c.getMembership().getDiscountPercentage());
                    if (c.getMembership().hasBuffetLounge()) {
                        System.out.print(" + buffet lounge access");
                    }
                    System.out.print("]");
                }
                System.out.printf(" - Amount to be paid: %.2f%n", amount);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No rooms are currently booked.");
        }
    }

    

    public void cancelBooking(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isBooked()) {
                Customer c = room.getBookedBy();
                room.book(null);
                room.bookedBy = null;
                room.isBooked = false;
                System.out.println(HotelBookingSystem.GREEN + "Booking for Room " + roomNumber + " canceled. "
                        + c.getName() + " has been removed." + HotelBookingSystem.RESET);
                return;
            }
        }
        System.out.println(HotelBookingSystem.RED + "Room not found or not currently booked." + HotelBookingSystem.RESET);
    }
}

public class HotelBookingSystem {
    static String adminUsername = "admin";
    static String adminPassword = "123";

    public static final String GREEN = "\u001B[32m";
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String RED = "\u001B[31m";

    static Scanner sc = new Scanner(System.in);
    static Hotel hotel = new Hotel(10, 5);

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getChoice();

            switch (choice) {
                case 1 -> {
                    displayLoading();
                    adminScreen();
                }
                case 2 -> {
                    displayLoading();
                    Question();
                }
                case 3 -> {
                    displayLoading();
                    Exit();
                    System.exit(0);
                }
                default -> System.out.println(RED + "Invalid option. Please select 1, 2, or 3." + RESET);
            }
        }
    }

    static void displayMenu() {
        System.out.println("""



                █ █ █▀█ ▀█▀ █▀▀ █   █▄▄ █▀█ █▀█ █▄▀ █ █▄ █ █▀▀
                █▀█ █▄█  █  ██▄ █▄▄ █▄█ █▄█ █▄█ █ █ █ █ ▀█ █▄█
                  [1] Admin Login
                  [2] Hotel Booking
                  [3] Exit

""");
    }

    static void displayLoading() {
        System.out.print("Loading");
        try {
            for (int i = 0; i < 3; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
        } catch (InterruptedException ignored) {
        }
        System.out.println();
    }

    static int getChoice() {
        int choice = -1;
        System.out.print("                  Choose an option: ");
        if (sc.hasNextInt()) {
            choice = sc.nextInt();
            sc.nextLine();
        } else {
            sc.nextLine();
            System.out.println(RED + "Invalid input. Please enter a number." + RESET);
        }
        return choice;
    }

    static void Question() {
        while (true) {
            System.out.print("Enter customer name (or 'exit' to quit): ");
            String customerName = sc.nextLine();
            if (customerName.equalsIgnoreCase("exit") || customerName.trim().isEmpty()) break;

            int age = readInt("Enter age (18+): ", 18, 99);
            String contactNumber = readValidated("Enter contact number (11 digits): ", "\\d{11}", "Invalid contact number.");
            String email = readValidated("Enter email: ", ".*@.*\\..*", "Invalid email.");
            String checkInTime = readValidated("Enter check-in time (hh:mm AM/PM): ",
                    "^(0[1-9]|1[0-2]):[0-5][0-9] (AM|PM|am|pm)$", "Invalid time format.");
            String checkOutTime = readValidated("Enter check-out time (hh:mm AM/PM): ",
                    "^(0[1-9]|1[0-2]):[0-5][0-9] (AM|PM|am|pm)$", "Invalid time format.");
            String date = readValidated("Enter date (MM DD YYYY): ", "\\d{2} \\d{2} \\d{4}", "Invalid date format.");
            int numberOfPeople = readInt("Enter number of people: ", 1, Integer.MAX_VALUE);

            boolean isVIP = readYesNo("Is this a VIP customer? (yes/no): ");
            Membership membership = null;

            if (readYesNo("Does this customer have a membership? (yes/no): ")) {
                String membershipId = "SILVER123"; // Fixed membership ID
                String membershipType = readValidated("Enter membership type (Silver|Gold|Platinum): ", "Silver|Gold|Platinum", "Invalid membership type.");
                boolean buffetLounge = readYesNo("Does this membership include buffet lounge access? (yes/no): ");
                membership = new Membership(membershipId, membershipType, buffetLounge);
                System.out.println("Discount percentage automatically set to " + membership.getDiscountPercentage() + "% based on membership type.");
            }

            Customer customer = new Customer(customerName, age, contactNumber, email, checkInTime, checkOutTime, date, numberOfPeople, membership);
            hotel.addToQueue(customer, isVIP);
            hotel.bookRoom();
        }
    }

    static int readInt(String prompt, int min, int max) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(sc.nextLine());
                if (value >= min && value <= max) return value;
                System.out.println("Enter a number between " + min + " and " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Invalid number.");
            }
        }
    }

    static String readValidated(String prompt, String regex, String errorMsg) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = sc.nextLine();
            if (input.matches(regex)) return input;
            System.out.println(errorMsg);
        }
    }

    static boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim().toLowerCase();
            if (input.equals("yes")) return true;
            if (input.equals("no")) return false;
            System.out.println("Please enter 'yes' or 'no'.");
        }
    }

    public static void adminScreen() {
        System.out.println("\nAdmin Login\n");
        adminLogin();
        showAdminOptions();
    }

    public static void adminLogin() {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print("Username: ");
            String username = sc.nextLine().trim();
            System.out.print("Password: ");
            String password = sc.nextLine().trim();

            if (username.equals(adminUsername) && password.equals(adminPassword)) {
                System.out.println(GREEN + "Login successful!" + RESET);
                return;
            } else {
                System.out.println(RED + "Incorrect credentials. Try again." + RESET);
                attempts++;
            }
        }
        System.out.println(RED + "Exceeded maximum login attempts." + RESET);
    }

    public static void showAdminOptions() {
        while (true) {
            System.out.println(YELLOW + """

            ┌───────────────────────────────┐
            │         Admin Panel           │
            └───────────────────────────────┘
            [1] View all rooms
            [2] View customer queues
            [3] Book next room
            [4] View booked customers
            [5] Cancel a booking
            [6] View customers with buffet lounge access benefit
            [7] Logout
            """ + RESET);
            System.out.print("Choose an option: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> hotel.viewRooms();
                case "2" -> hotel.viewQueues();
                case "3" -> hotel.bookRoom();
                case "4" -> hotel.viewBookedRooms();
                case "5" -> {
                    System.out.print("Enter the room number to cancel booking: ");
                    try {
                        int roomNum = Integer.parseInt(sc.nextLine());
                        hotel.cancelBooking(roomNum);
                    } catch (NumberFormatException e) {
                        System.out.println(RED + "Invalid room number format." + RESET);
                    }
                }
                case "6" -> hotel.viewCustomersWithFreeCondom();
                case "7" -> {
                    System.out.println(GREEN + "Logged out of Admin Panel." + RESET);
                    return;
                }
                default -> System.out.println(RED + "Invalid option. Try again." + RESET);
            }
        }
    }

    public static void Exit() {
        System.out.println("\nThank you for using the Hotel Booking System. Goodbye!\n");
    }
}

