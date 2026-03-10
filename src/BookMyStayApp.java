import java.util.Queue;
import java.util.LinkedList;

public class BookMyStayApp {

    /*
     ==========================================
     CLASS - Reservation
     ==========================================
     Represents a booking request made by a guest
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
     ==========================================
     CLASS - BookingRequestQueue
     ==========================================
     Handles booking requests using FIFO queue
     */

    static class BookingRequestQueue {

        private Queue<Reservation> requestQueue;

        public BookingRequestQueue() {
            requestQueue = new LinkedList<>();
        }

        public void addRequest(Reservation reservation) {
            requestQueue.offer(reservation);
        }

        public Reservation getNextRequest() {
            return requestQueue.poll();
        }

        public boolean hasPendingRequests() {
            return !requestQueue.isEmpty();
        }
    }


    /*
     ==========================================
     UC5 MAIN LOGIC
     ==========================================
     */

    public static void main(String[] args) {

        System.out.println("Booking Request Queue");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        Reservation r1 = new Reservation("Abhi", "Single");
        Reservation r2 = new Reservation("Subha", "Double");
        Reservation r3 = new Reservation("Vanmathi", "Suite");

        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        System.out.println("\nProcessing Requests (FIFO):");

        while (bookingQueue.hasPendingRequests()) {

            Reservation next = bookingQueue.getNextRequest();

            System.out.println("Guest: " + next.getGuestName());
            System.out.println("Requested Room: " + next.getRoomType());
            System.out.println("-----------------------");
        }
    }
}