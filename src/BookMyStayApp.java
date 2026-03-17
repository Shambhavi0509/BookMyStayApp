import java.util.*;
import java.io.*;

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

        public Map<String, Integer> getInventory() {
            return inventory;
        }

        public void setInventory(String roomType, int count) {
            inventory.put(roomType, count);
        }

        public void displayInventory() {
            System.out.println("\nCurrent Inventory:");
            for (String key : inventory.keySet()) {
                System.out.println(key + ": " + inventory.get(key));
            }
        }
    }

    /*
    =====================================================
    CLASS - FilePersistenceService
    Use Case 12: Data Persistence & System Recovery
    =====================================================
    */
    static class FilePersistenceService {

        // Save inventory to file
        public void saveInventory(RoomInventory inventory, String filePath) {

            try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

                for (Map.Entry<String, Integer> entry : inventory.getInventory().entrySet()) {
                    writer.println(entry.getKey() + "=" + entry.getValue());
                }

                System.out.println("Inventory saved successfully.");

            } catch (IOException e) {

                System.out.println("Error saving inventory: " + e.getMessage());
            }
        }

        // Load inventory from file
        public void loadInventory(RoomInventory inventory, String filePath) {

            File file = new File(filePath);

            if (!file.exists()) {
                System.out.println("No valid inventory data found. Starting fresh.");
                return;
            }

            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

                String line;

                while ((line = reader.readLine()) != null) {

                    String[] parts = line.split("=");

                    String roomType = parts[0];
                    int count = Integer.parseInt(parts[1]);

                    inventory.setInventory(roomType, count);
                }

                System.out.println("Inventory restored successfully.");

            } catch (Exception e) {

                System.out.println("Error loading inventory. Starting fresh.");
            }
        }
    }

    /*
    =====================================================
    MAIN METHOD
    Use Case 12
    =====================================================
    */
    public static void main(String[] args) {

        System.out.println("System Recovery");

        String filePath = "inventory.txt";

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService persistenceService = new FilePersistenceService();

        // Load inventory from file
        persistenceService.loadInventory(inventory, filePath);

        // Display inventory
        inventory.displayInventory();

        // Save inventory
        persistenceService.saveInventory(inventory, filePath);
    }
}