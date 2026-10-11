import java.io.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class RecyclingManager {
    // Task 2: Create HashMap collection to map unique ID -> Household object
    private Map<String, Household> householdMap;
    private static final String FILE_NAME = "HouseholdData.ser";

    public RecyclingManager() {
        this.householdMap = new HashMap<>();
        loadData(); // Task 5: Automatically load saved data on initialization
    }

    // Task 3: Register a household with id, name, address, and joinDate
    public void registerHousehold(String id, String name, String address) {
        if (householdMap.containsKey(id)) {
            System.out.println("Household with ID " + id + " already exists.");
            return;
        }

        LocalDate joinDate = LocalDate.now();

        // Create a new Household object and add it to the HashMap
        Household household = new Household(id, name, address, joinDate);
        householdMap.put(id, household);

        System.out.println("Household registered successfully: " + household + " Name: " + name);
    }

    // Task 4: Log a household recycling event
    public void logRecyclingEvent(String householdId, String materialType, double weightKg) {
        Household household = householdMap.get(householdId);
        if (household == null) {
            System.out.println("Household with ID " + householdId + " not found.");
            return;
        }
        if (weightKg <= 0) {
            System.out.println("Weight must be above 0kgs.");
            return;
        }
        RecyclingEvent event = new RecyclingEvent(materialType, weightKg, LocalDate.now());
        household.addEvent(event); // Fixed: matched Household.java method name

        System.out.println("Event logged! Household ID: " + householdId + 
                " earned " + event.getPointsEarned() + " EcoPoints for " + weightKg + " kg of " + materialType + ".");
    }

    // Task 5: Save Household and Event Data to File
    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(householdMap);
            System.out.println("Data saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Task 5: Load Household and Event Data from File
    @SuppressWarnings("unchecked")
    private void loadData() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            householdMap = (HashMap<String, Household>) ois.readObject();
            System.out.println("Data successfully loaded from " + FILE_NAME);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }
    // Task 6: Display all registered households
    public void displayAllHouseholds() {
        if (householdMap.isEmpty()) {
            System.out.println("No households registered yet.");
            return;
        }
        System.out.println("\n--- Registered Households ---");
        for (Household h : householdMap.values()) {
            System.out.println(h);
        }
    }

    // Task 6: Display details and recycling history for a specific household
    public void displayHouseholdDetails(String householdId) {
        Household h = householdMap.get(householdId);
        if (h == null) {
            System.out.println("Household with ID " + householdId + " not found.");
            return;
        }

        System.out.println("\n--- Household Details ---");
        System.out.println("ID: " + h.getId() + " | Name: " + h.getName() + " | Address: " + h.getAddress());
        System.out.println("Join Date: " + h.getJoinDate());
        System.out.println("Total Points: " + h.getTotalPoints() + " | Total Weight Recycled: " + h.getTotalWeightRecycled() + " kg");
        
        System.out.println("Recycling History:");
        if (h.getEvents().isEmpty()) {
            System.out.println("  No recycling events logged yet.");
        } else {
            for (RecyclingEvent event : h.getEvents()) {
                System.out.println("  - " + event);
            }
        }
    }

    // Task 7: Generate community reports
    public void generateReports() {
        if (householdMap.isEmpty()) {
            System.out.println("No data available to generate reports.");
            return;
        }

        double totalCommunityWeight = 0.0;
        Household topRecycler = null;

        for (Household h : householdMap.values()) {
            totalCommunityWeight += h.getTotalWeightRecycled();

            if (topRecycler == null || h.getTotalPoints() > topRecycler.getTotalPoints()) {
                topRecycler = h;
            }
        }

        System.out.println("\n========== COMMUNITY RECYCLING REPORT ==========");
        System.out.printf("Total Community Recycled Weight: %.2f kg\n", totalCommunityWeight);
        
        if (topRecycler != null && topRecycler.getTotalPoints() > 0) {
            System.out.println("Top Recycler: " + topRecycler.getName() + 
                    " (ID: " + topRecycler.getId() + ") with " + topRecycler.getTotalPoints() + " EcoPoints!");
        } else {
            System.out.println("Top Recycler: No points accumulated yet.");
        }
        System.out.println("================================================");
    }
    
    // Getter for verification
    public Map<String, Household> getHouseholdMap() {
        return householdMap;
    }
}