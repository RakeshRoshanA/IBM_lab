import java.util.HashMap;
import java.util.Map;
import java.util.LocalDate;

public class RecyclingManager {
    // Task 2: Create HashMap collection to map unique ID -> Household object
    private Map<String, Household> householdMap;

    public RecyclingManager() {
        this.householdMap = new HashMap<>();
    }


    //Registering a household with id, naem, address and joindate
    public void registerHousehold(String id, String name, String address) {
        if(householdMap.containsKey(id)) {{
            System.out.println("Household with ID " + id + " already exists.");
            return;
        }
        
        LocalDate joinDate = LocalDate.now();

        // Task 3: Create a new Household object and add it to the HashMap
        Household household = new Household(id, name, address, joinDate);
        householdMap.put(id, household);

        System.out.println("Household registered successfully: " + household + " Name: " + name);
    }

    // Getter for testing/verification
    public Map<String, Household> getHouseholdMap() {
        return householdMap;
    }
}