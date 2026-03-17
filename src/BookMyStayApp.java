import java.util.*;

public class BookMyStayApp {

    /*
    =====================================================
    CLASS - Reservation
    =====================================================
    */
    static class Reservation {

        private String guestName;
        private String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public String getGuestName() {
            return guestName;
        }

        public String getRoomType() {
            return roomType;
        }
    }

    /*
    =====================================================
    CLASS - RoomInventory
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

        public boolean isAvailable(String roomType) {
            return inventory.get(roomType) > 0;
        }

        public String allocate(String roomType) {

            if (!isAvailable(roomType)) {
                return null;
            }

            int roomNumber = inventory.get(roomType);
            inventory.put(roomType, roomNumber - 1);

            return roomType + "-" + roomNumber;
        }

        public void showInventory() {

            System.out.println("\nRemaining Inventory:");
            for (String key : inventory.keySet()) {
                System.out.println(key + ": " + inventory.get(key));
            }
        }
    }

    /*
    =====================================================
    CLASS - BookingRequestQueue
    =====================================================
    */
    static class BookingRequestQueue {

        private Queue<Reservation> queue = new LinkedList<>();

        public void addRequest(Reservation r) {
            queue.add(r);
        }

        public Reservation getRequest() {
            return queue.poll();
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    /*
    =====================================================
    CLASS - RoomAllocationService
    =====================================================
    */
    static class RoomAllocationService {

        public void allocateRoom(Reservation reservation, RoomInventory inventory) {

            String roomId = inventory.allocate(reservation.getRoomType());

            if (roomId != null) {

                System.out.println(
                        "Booking confirmed for Guest: "
                                + reservation.getGuestName()
                                + ", Room ID: "
                                + roomId
                );

            } else {

                System.out.println(
                        "Booking failed for Guest: "
                                + reservation.getGuestName()
                                + ", No rooms available."
                );
            }
        }
    }

    /*
    =====================================================
    CLASS - ConcurrentBookingProcessor
    =====================================================
    */
    static class ConcurrentBookingProcessor implements Runnable {

        private BookingRequestQueue bookingQueue;
        private RoomInventory inventory;
        private RoomAllocationService allocationService;

        public ConcurrentBookingProcessor(
                BookingRequestQueue bookingQueue,
                RoomInventory inventory,
                RoomAllocationService allocationService
        ) {
            this.bookingQueue = bookingQueue;
            this.inventory = inventory;
            this.allocationService = allocationService;
        }

        @Override
        public void run() {

            while (true) {

                Reservation reservation;

                // synchronized queue access
                synchronized (bookingQueue) {

                    if (bookingQueue.isEmpty()) {
                        break;
                    }

                    reservation = bookingQueue.getRequest();
                }

                // synchronized inventory allocation
                synchronized (inventory) {
                    allocationService.allocateRoom(reservation, inventory);
                }
            }
        }
    }

    /*
    =====================================================
    MAIN METHOD
    Use Case 11
    =====================================================
    */
    public static void main(String[] args) {

        System.out.println("Concurrent Booking Simulation");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();

        // Add booking requests
        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Vannathi", "Double"));
        bookingQueue.addRequest(new Reservation("Kunal", "Suite"));
        bookingQueue.addRequest(new Reservation("Subha", "Single"));

        // Create processor threads
        Thread t1 = new Thread(
                new ConcurrentBookingProcessor(
                        bookingQueue,
                        inventory,
                        allocationService
                )
        );

        Thread t2 = new Thread(
                new ConcurrentBookingProcessor(
                        bookingQueue,
                        inventory,
                        allocationService
                )
        );

        // start threads
        t1.start();
        t2.start();

        try {

            t1.join();
            t2.join();

        } catch (InterruptedException e) {

            System.out.println("Thread execution interrupted.");
        }

        inventory.showInventory();
    }
}