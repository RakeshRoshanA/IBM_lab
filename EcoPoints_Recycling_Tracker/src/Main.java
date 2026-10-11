import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        RecyclingManager manager = new RecyclingManager();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("==========================================");
        System.out.println(" Welcome to EcoPoints Recycling Tracker ");
        System.out.println("==========================================");

        while (running) {
            System.out.println("\nSelect an Option:");
            System.out.println("1. Register Household");
            System.out.println("2. Log Recycling Event");
            System.out.println("3. Display All Households");
            System.out.println("4. View Household History & Details");
            System.out.println("5. Generate Community Report");
            System.out.println("6. Save & Exit");
            System.out.print("Enter your choice (1-6): ");

            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                switch (choice) {
                    case 1:
                        System.out.print("Enter Household ID: ");
                        String id = scanner.nextLine().trim();
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine().trim();
                        System.out.print("Enter Address: ");
                        String address = scanner.nextLine().trim();
                        manager.registerHousehold(id, name, address);
                        break;

                    case 2:
                        System.out.print("Enter Household ID: ");
                        String hId = scanner.nextLine().trim();
                        System.out.print("Enter Material Type (plastic, glass, metal, paper): ");
                        String material = scanner.nextLine().trim();
                        System.out.print("Enter Weight (kg): ");
                        double weight = Double.parseDouble(scanner.nextLine().trim());
                        manager.logRecyclingEvent(hId, material, weight);
                        break;

                    case 3:
                        manager.displayAllHouseholds();
                        break;

                    case 4:
                        System.out.print("Enter Household ID: ");
                        String searchId = scanner.nextLine().trim();
                        manager.displayHouseholdDetails(searchId);
                        break;

                    case 5:
                        manager.generateReports();
                        break;

                    case 6:
                        manager.saveData(); // Task 5: Save before exit
                        running = false;
                        System.out.println("Exiting program. Thank you for recycling!");
                        break;

                    default:
                        System.out.println("[Error] Invalid choice! Please select an option between 1 and 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("[Error] Invalid input format! Please enter numbers for choices and weights.");
            } catch (Exception e) {
                System.out.println("[Error] An unexpected error occurred: " + e.getMessage());
            }
        }

        scanner.close();
    }
}