import java.util.*;

public class BookMyStayApp {

    /*
    =====================================================
    CLASS - Reservation
    Represents a confirmed booking.
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
    CLASS - BookingHistory
    Use Case 8: Booking History & Reporting
    Maintains confirmed reservations.
    =====================================================
    */
    static class BookingHistory {

        // List storing confirmed reservations
        private List<Reservation> confirmedReservations;

        // Constructor
        public BookingHistory() {
            confirmedReservations = new ArrayList<>();
        }

        // Add reservation to history
        public void addReservation(Reservation reservation) {
            confirmedReservations.add(reservation);
        }

        // Return all reservations
        public List<Reservation> getConfirmedReservations() {
            return confirmedReservations;
        }
    }

    /*
    =====================================================
    CLASS - BookingReportService
    Generates reports from booking history
    =====================================================
    */
    static class BookingReportService {

        public void generateReport(BookingHistory history) {

            System.out.println("\nBooking History Report");

            for (Reservation r : history.getConfirmedReservations()) {

                System.out.println(
                        "Guest: " + r.getGuestName() +
                                ", Room Type: " + r.getRoomType()
                );
            }
        }
    }

    /*
    =====================================================
    MAIN METHOD
    Demonstrates Use Case 8
    =====================================================
    */

    public static void main(String[] args) {

        System.out.println("Booking History and Reporting");

        // Create booking history
        BookingHistory history = new BookingHistory();

        // Add confirmed reservations
        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vannathi", "Suite"));

        // Generate report
        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history);
    }
}