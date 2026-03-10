import java.util.HashMap;

public class BookMyStayApp {

    // Centralized inventory using HashMap
    static HashMap<String, Integer> inventory = new HashMap<>();

    // Initialize room availability
    public static void initializeInventory() {
        inventory.put("Single Room", 10);
        inventory.put("Double Room", 5);
        inventory.put("Suite Room", 2);
    }

    // Get availability
    public static int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public static void updateAvailability(String roomType, int newCount) {
        inventory.put(roomType, newCount);
    }

    // Display inventory
    public static void displayInventory() {
        System.out.println("=== Current Room Inventory ===");

        for (String room : inventory.keySet()) {
            System.out.println(room + " : " + inventory.get(room));
        }
    }

    public static void main(String[] args) {

        // Initialize inventory
        initializeInventory();

        // Display inventory
        displayInventory();

        // Check availability
        System.out.println("\nSingle Room Available: " + getAvailability("Single Room"));

        // Update availability
        updateAvailability("Single Room", 8);

        System.out.println("\nUpdated Inventory:");
        displayInventory();
    }
}