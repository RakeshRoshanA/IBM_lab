import java.util.HashMap;
import java.util.Map;

public class RecyclingManager {
    // Task 2: Create HashMap collection to map unique ID -> Household object
    private Map<String, Household> householdMap;

    public RecyclingManager() {
        this.householdMap = new HashMap<>();
    }

    // Getter for testing/verification
    public Map<String, Household> getHouseholdMap() {
        return householdMap;
    }
}