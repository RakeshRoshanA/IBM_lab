import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Household implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String address;
    private LocalDate joinDate;
    private List<RecyclingEvent> events;

    // Constructor (Must be public so RecyclingManager can access it)
    public Household(String id, String name, String address, LocalDate joinDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.joinDate = joinDate;
        this.events = new ArrayList<>();
    }

    // Add a recycling event to this household's list
    public void addEvent(RecyclingEvent event) {
        this.events.add(event);
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public List<RecyclingEvent> getEvents() {
        return events;
    }

    // Calculate total points earned by this household
    public int getTotalPoints() {
        int total = 0;
        for (RecyclingEvent event : events) {
            total += event.getPointsEarned();
        }
        return total;
    }

    // Calculate total weight recycled by this household
    public double getTotalWeightRecycled() {
        double totalWeight = 0.0;
        for (RecyclingEvent event : events) {
            totalWeight += event.getWeightKg();
        }
        return totalWeight;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Address: " + address + 
               " | Join Date: " + joinDate + " | Total Points: " + getTotalPoints();
    }
}