import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Vehicle> vehicles = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Sample data pre-populated
        try {
            vehicles.add(new Car("ABC123", "Toyota Vios", 1500.0, 4));
            vehicles.add(new Van("XYZ789", "Toyota Hiace", 2000.0, 1200));
            vehicles.add(new Motorcycle("MNO456", "Honda Click", 500.0, 150));
        } catch (IllegalArgumentException e) {
            System.out.println("Error adding initial data: " + e.getMessage());
        }

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readIntInput("Enter option: ");
            switch (choice) {
                case 1:
                    addVehicle();
                    break;
                case 2:
                    viewAllVehicles();
                    break;
                case 3:
                    rentVehicle();
                    break;
                case 4:
                    returnVehicle();
                    break;
                case 5:
                    System.out.println("Exiting system. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please select an option between 1 and 5.");
                    break;
            }
            System.out.println();
        }
    }

    private static void displayMenu() {
        System.out.println("=== VEHICLE RENTAL SYSTEM ===");
        System.out.println("1. Add Vehicle");
        System.out.println("2. View All Vehicles");
        System.out.println("3. Rent a Vehicle");
        System.out.println("4. Return a Vehicle");
        System.out.println("5. Exit");
    }

    private static void addVehicle() {
        System.out.println("\n--- Add Vehicle ---");
        System.out.println("1. Car");
        System.out.println("2. Motorcycle");
        System.out.println("3. Van");
        int typeChoice = readIntInput("Select vehicle type: ");

        if (typeChoice < 1 || typeChoice > 3) {
            System.out.println("Invalid vehicle type selection!");
            return;
        }

        String plateNumber = readValidPlateNumberForCreation();
        if (plateNumber == null) return;

        String model = readNonEmptyString("Enter Model: ");
        double baseRate = readPositiveDouble("Enter Base Rate per Day (Php): ");

        try {
            Vehicle vehicle = null;
            switch (typeChoice) {
                case 1:
                    int seats = readPositiveInt("Enter Number of Seats: ");
                    vehicle = new Car(plateNumber, model, baseRate, seats);
                    break;
                case 2:
                    int cc = readPositiveInt("Enter Engine Displacement (cc): ");
                    vehicle = new Motorcycle(plateNumber, model, baseRate, cc);
                    break;
                case 3:
                    int capacity = readPositiveInt("Enter Cargo Capacity (kg): ");
                    vehicle = new Van(plateNumber, model, baseRate, capacity);
                    break;
            }

            if (vehicle != null) {
                vehicles.add(vehicle);
                System.out.println("Vehicle added successfully!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error creating vehicle: " + e.getMessage());
        }
    }

    private static void viewAllVehicles() {
        System.out.println("\n--- All Vehicles ---");
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles registered in the system.");
            return;
        }

        System.out.printf("%-12s | %-12s | %-15s | %-14s | %-12s | %-18s%n",
                "Type", "Plate No.", "Model", "Rate/Day", "Status", "Specific Details");
        System.out.println("--------------------------------------------------------------------------------------------");

        for (Vehicle v : vehicles) {
            String status = v.isAvailable() ? "Available" : "Rented";
            System.out.printf("%-12s | %-12s | %-15s | Php %-10.2f | %-12s | %-18s%n",
                    v.getType(), v.getPlateNumber(), v.getModel(), v.getBaseRate(), status, v.getSpecificDetail());
        }
    }

    private static void rentVehicle() {
        System.out.println("\n--- Rent a Vehicle ---");
        String plateNumber = readInputPlateNumber("Enter Plate Number: ");
        if (plateNumber == null) return;

        Vehicle vehicle = findVehicle(plateNumber);

        if (vehicle == null) {
            System.out.println("Vehicle not found!");
            return;
        }

        if (!vehicle.isAvailable()) {
            System.out.println("Vehicle is currently rented out!");
            return;
        }

        int days = readPositiveInt("Enter number of rental days: ");
        try {
            double totalCost = vehicle.calculateRentalCost(days);
            vehicle.setAvailable(false);
            System.out.printf("Total Rental Cost: Php %.2f%n", totalCost);
            System.out.println("Vehicle rented successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error during rental calculation: " + e.getMessage());
        }
    }

    private static void returnVehicle() {
        System.out.println("\n--- Return a Vehicle ---");
        String plateNumber = readInputPlateNumber("Enter Plate Number: ");
        if (plateNumber == null) return;

        Vehicle vehicle = findVehicle(plateNumber);

        if (vehicle == null) {
            System.out.println("Vehicle not found!");
            return;
        }

        if (vehicle.isAvailable()) {
            System.out.println("Vehicle was not rented out.");
            return;
        }

        vehicle.setAvailable(true);
        System.out.println("Vehicle returned successfully!");
    }

    // --- Helper Validation Methods ---

    private static Vehicle findVehicle(String plateNumber) {
        if (plateNumber == null) return null;
        for (Vehicle v : vehicles) {
            if (v.getPlateNumber().equalsIgnoreCase(plateNumber.trim())) {
                return v;
            }
        }
        return null;
    }

    private static String readValidPlateNumberForCreation() {
        while (true) {
            System.out.print("Enter Plate Number (Alphanumeric): ");
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Plate number cannot be empty.");
                continue;
            }
            if (!input.matches("^[a-zA-Z0-9]+$")) {
                System.out.println("Invalid input! Plate number must contain only letters and digits without spaces.");
                continue;
            }
            if (findVehicle(input) != null) {
                System.out.println("Error: A vehicle with this Plate Number already exists!");
                return null;
            }
            return input.toUpperCase();
        }
    }

    private static String readInputPlateNumber(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            System.out.println("Plate number cannot be empty.");
            return null;
        }
        return input.toUpperCase();
    }

    private static String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("Input cannot be empty or spaces only.");
        }
    }

    private static int readIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine().trim();
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid whole integer.");
            }
        }
    }

    private static int readPositiveInt(String prompt) {
        while (true) {
            int val = readIntInput(prompt);
            if (val > 0) {
                return val;
            }
            System.out.println("Value must be strictly greater than 0.");
        }
    }

    private static double readPositiveDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String line = scanner.nextLine().trim();
                double val = Double.parseDouble(line);
                if (Double.isNaN(val) || Double.isInfinite(val)) {
                    System.out.println("Invalid number! Please enter a standard numeric value.");
                    continue;
                }
                if (val > 0) {
                    return val;
                }
                System.out.println("Value must be strictly greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid decimal number.");
            }
        }
    }
}