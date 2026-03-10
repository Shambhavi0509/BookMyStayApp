public class BookMyStayApp {

    public static void main(String[] args) {

        // Room attributes
        String singleRoomType = "Single Room";
        int singleBeds = 1;
        double singlePrice = 2000;

        String doubleRoomType = "Double Room";
        int doubleBeds = 2;
        double doublePrice = 3500;

        String suiteRoomType = "Suite Room";
        int suiteBeds = 3;
        double suitePrice = 6000;

        // Static availability variables
        int singleAvailability = 10;
        int doubleAvailability = 5;
        int suiteAvailability = 2;

        System.out.println("=== Room Details ===");

        System.out.println("\nRoom Type: " + singleRoomType);
        System.out.println("Beds: " + singleBeds);
        System.out.println("Price: " + singlePrice);
        System.out.println("Available: " + singleAvailability);

        System.out.println("\nRoom Type: " + doubleRoomType);
        System.out.println("Beds: " + doubleBeds);
        System.out.println("Price: " + doublePrice);
        System.out.println("Available: " + doubleAvailability);

        System.out.println("\nRoom Type: " + suiteRoomType);
        System.out.println("Beds: " + suiteBeds);
        System.out.println("Price: " + suitePrice);
        System.out.println("Available: " + suiteAvailability);

        System.out.println("\nApplication Finished.");
    }
}