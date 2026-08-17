import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.time.LocalDate;

public class Household implements Serializable {
    private static fial long serialVersionUID = 1L;

    private string id;
    private String name;
    private String address;
    private Local joinDate;
    private List<RecyclingEvent> events;

    private Household(String id, String name, String address, LocalDate joinDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.joinDate = joinDate;
        this.events = new ArrayList<>();
    }

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

    public void addRecyclingEvent(RecyclingEvent event) {
        events.add(event);
    }

    public double getTotalWeightRecycled(){
        double total = 0'
        for(RecyclingEvent event : events){
            total += event.getWeightKg();
        }
        return total;
    }

    public int getTotalPointsEarned(){
        int total = 0;
        for(RecyclingEvent event : events){
            total += event.getPointsEarned();
        }
        return total;
    }

    @Override
    public String toString() {
        return String.format("Household ID: %s, Name: %s, Address: %s, Join Date: %s, Total Weight Recycled: %.2f kg, Total Points Earned: %d", 
            id, name, address, joinDate.toString(), getTotalWeightRecycled(), getTotalPointsEarned());
    }
}