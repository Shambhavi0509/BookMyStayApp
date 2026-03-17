import java.util.*;

public class BookMyStayApp {

    /*
    =====================================================
    CLASS - RoomInventory
    Maintains room availability
    =====================================================
    */
    static class RoomInventory {

        private Map<String, Integer> inventory;

        public RoomInventory() {
            inventory = new HashMap<>();

            inventory.put("Single", 5);
            inventory.put("Double", 3);
            inventory.put("Suite", 2);
        }

        public void restoreRoom(String roomType) {
            inventory.put(roomType, inventory.get(roomType) + 1);
        }

        public int getAvailability(String roomType) {
            return inventory.get(roomType);
        }
    }

    /*
    =====================================================
    CLASS - CancellationService
    Use Case 10: Booking Cancellation & Inventory Rollback
    =====================================================
    */
    static class CancellationService {

        // Stack storing recently released room IDs
        private Stack<String> releasedRoomIds;

        // Map: Reservation ID -> Room Type
        private Map<String, String> reservationRoomTypeMap;

        public CancellationService() {
            releasedRoomIds = new Stack<>();
            reservationRoomTypeMap = new HashMap<>();
        }

        // Register confirmed booking
        public void registerBooking(String reservationId, String roomType) {
            reservationRoomTypeMap.put(reservationId, roomType);
        }

        // Cancel booking
        public void cancelBooking(String reservationId, RoomInventory inventory) {

            if (!reservationRoomTypeMap.containsKey(reservationId)) {
                System.out.println("Cancellation failed. Reservation not found.");
                return;
            }

            String roomType = reservationRoomTypeMap.get(reservationId);

            // restore inventory
            inventory.restoreRoom(roomType);

            // track rollback history
            releasedRoomIds.push(reservationId);

            // remove reservation
            reservationRoomTypeMap.remove(reservationId);

            System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
        }

        // Display rollback history
        public void showRollbackHistory() {

            System.out.println("\nRollback History (Most Recent First):");

            for (int i = releasedRoomIds.size() - 1; i >= 0; i--) {
                System.out.println("Released Reservation ID: " + releasedRoomIds.get(i));
            }
        }
    }

    /*
    =====================================================
    MAIN METHOD
    Demonstrates Use Case 10
    =====================================================
    */
    public static void main(String[] args) {

        System.out.println("Booking Cancellation");

        RoomInventory inventory = new RoomInventory();
        CancellationService cancellationService = new CancellationService();

        // simulate confirmed booking
        String reservationId = "Single-1";
        cancellationService.registerBooking(reservationId, "Single");

        // cancel booking
        cancellationService.cancelBooking(reservationId, inventory);

        // show rollback history
        cancellationService.showRollbackHistory();

        System.out.println("\nUpdated Single Room Availability: " +
                inventory.getAvailability("Single"));
    }
}