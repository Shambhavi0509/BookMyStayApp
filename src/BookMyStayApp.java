import java.util.*;

public class BookMyStayApp {

    /*
    =====================================================
    CLASS - InvalidBookingException
    Use Case 9: Error Handling & Validation
    =====================================================
    Represents invalid booking scenarios.
    */

    static class InvalidBookingException extends Exception {

        public InvalidBookingException(String message) {
            super(message);
        }
    }

    /*
    =====================================================
    CLASS - RoomInventory
    Maintains available rooms
    =====================================================
    */

    static class RoomInventory {

        private Map<String, Integer> inventory;

        public RoomInventory() {

            inventory = new HashMap<>();

            inventory.put("Single", 2);
            inventory.put("Double", 2);
            inventory.put("Suite", 1);
        }

        public boolean isRoomAvailable(String roomType) {

            Integer count = inventory.get(roomType);

            return count != null && count > 0;
        }

        public void allocateRoom(String roomType) {

            inventory.put(roomType, inventory.get(roomType) - 1);
        }
    }

    /*
    =====================================================
    CLASS - ReservationValidator
    Validates booking input
    =====================================================
    */

    static class ReservationValidator {

        public void validate(
                String guestName,
                String roomType,
                RoomInventory inventory
        ) throws InvalidBookingException {

            if (guestName == null || guestName.trim().isEmpty()) {
                throw new InvalidBookingException("Guest name cannot be empty.");
            }

            if (!roomType.equals("Single") &&
                    !roomType.equals("Double") &&
                    !roomType.equals("Suite")) {

                throw new InvalidBookingException("Invalid room type selected.");
            }

            if (!inventory.isRoomAvailable(roomType)) {
                throw new InvalidBookingException("Selected room type is unavailable.");
            }
        }
    }

    /*
    =====================================================
    CLASS - BookingRequestQueue
    Stores booking requests
    =====================================================
    */

    static class BookingRequestQueue {

        private Queue<String> queue;

        public BookingRequestQueue() {
            queue = new LinkedList<>();
        }

        public void addRequest(String guestName) {
            queue.add(guestName);
        }
    }

    /*
    =====================================================
    MAIN METHOD
    Demonstrates Use Case 9
    =====================================================
    */

    public static void main(String[] args) {

        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {

            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            // Validate input
            validator.validate(guestName, roomType, inventory);

            // Add request
            bookingQueue.addRequest(guestName);

            inventory.allocateRoom(roomType);

            System.out.println("Booking successful!");

        } catch (InvalidBookingException e) {

            System.out.println("Booking failed: " + e.getMessage());

        } finally {

            scanner.close();
        }
    }
}