import java.util.*;

public class BookMyStayApp {

    /*
    ====================================================
    CLASS - Service
    Use Case 7: Add-On Service Selection
    ====================================================
    Represents an optional service that can be added
    to a confirmed reservation.
    Examples:
    - Breakfast
    - Spa
    - Airport Pickup
    */

    static class Service {

        // Name of the service
        private String serviceName;

        // Cost of the service
        private double cost;

        // Constructor
        public Service(String serviceName, double cost) {
            this.serviceName = serviceName;
            this.cost = cost;
        }

        // Getter for service name
        public String getServiceName() {
            return serviceName;
        }

        // Getter for service cost
        public double getCost() {
            return cost;
        }
    }

    /*
    ====================================================
    CLASS - AddOnServiceManager
    Use Case 7: Add-On Service Selection
    ====================================================
    Manages optional services associated with reservations.
    */

    static class AddOnServiceManager {

        /*
         Maps reservation ID to selected services.

         Key   -> Reservation ID
         Value -> List of selected services
        */
        private Map<String, List<Service>> servicesByReservation;

        // Constructor
        public AddOnServiceManager() {
            servicesByReservation = new HashMap<>();
        }

        // Attach service to reservation
        public void addService(String reservationId, Service service) {

            servicesByReservation
                    .computeIfAbsent(reservationId, k -> new ArrayList<>())
                    .add(service);
        }

        // Calculate total service cost
        public double calculateTotalServiceCost(String reservationId) {

            List<Service> services = servicesByReservation.get(reservationId);

            if (services == null) {
                return 0;
            }

            double total = 0;

            for (Service s : services) {
                total += s.getCost();
            }

            return total;
        }
    }

    /*
    ====================================================
    MAIN METHOD
    Demonstrates UC7
    ====================================================
    */

    public static void main(String[] args) {

        System.out.println("Add-On Service Selection");

        // Create manager
        AddOnServiceManager manager = new AddOnServiceManager();

        // Create services
        Service breakfast = new Service("Breakfast", 500);
        Service spa = new Service("Spa", 1000);

        // Example reservation
        String reservationId = "Single-1";

        // Attach services
        manager.addService(reservationId, breakfast);
        manager.addService(reservationId, spa);

        // Calculate total cost
        double totalCost = manager.calculateTotalServiceCost(reservationId);

        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}