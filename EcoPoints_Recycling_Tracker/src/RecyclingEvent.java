import java.util.Scanner;
import java.io.Serializable;
import java.time.LocalDate;

class RecyclingEvent{
    private static final long serialVersionUID = 1L;

    private String materialType;
    private double weightKg;
    private LocalDate recyclingDate;
    private int pointsearned;

    public RecyclingEvent(String materialType, double weightKg, LocalDate reyclingDate){
        if(weightKg <= 0){
            throw new IllegalArgumentException("Weight must be greater than 0kg");
        }
        this.materialType = materialType;
        this.weightKg = weightKg;
        this.recyclingDate = reyclingDate;
        this.pointsearned = (int) Math.round(weightKg * 10); // 10 points per kg
    }

    public String getMaterialType() {
        return materialType;
    }  
    public double getWeightKg() {
        return weightKg;
    }
    public LocalDate getRecyclingDate() {
        return recyclingDate;
    }
    public int getPointsEarned() {
        return pointsearned;
    }

    @Override
    public String toString() {
        return String.format("Recycling Event: %s, Weight: %.2f kg, Date: %s, Points Earned: %d", 
            materialType, weightKg, recyclingDate.toString(), pointsearned);
    }
}