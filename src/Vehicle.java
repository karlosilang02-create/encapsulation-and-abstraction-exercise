public abstract class Vehicle {
    private String plateNumber;
    private String model;
    private double baseRate;
    private boolean isAvailable;

    public Vehicle(String plateNumber, String model, double baseRate) {
        setPlateNumber(plateNumber);
        setModel(model);
        setBaseRate(baseRate);
        this.isAvailable = true; // Default state: available
    }

    // --- Abstraction ---
    public abstract double calculateRentalCost(int days);
    public abstract String getSpecificDetail();

    // --- Encapsulation with Strict Validation ---
    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        if (plateNumber == null || plateNumber.trim().isEmpty()) {
            throw new IllegalArgumentException("Plate number cannot be empty or blank.");
        }
        String cleaned = plateNumber.trim();
        if (!cleaned.matches("^[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException("Plate number must contain only alphanumeric characters (letters and numbers).");
        }
        this.plateNumber = cleaned.toUpperCase();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Model name cannot be empty or blank.");
        }
        this.model = model.trim();
    }

    public double getBaseRate() {
        return baseRate;
    }

    public void setBaseRate(double baseRate) {
        if (Double.isNaN(baseRate) || Double.isInfinite(baseRate)) {
            throw new IllegalArgumentException("Base rate must be a valid number.");
        }
        if (baseRate <= 0) {
            throw new IllegalArgumentException("Base rate per day must be strictly greater than 0.");
        }
        this.baseRate = baseRate;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    public String getType() {
        return this.getClass().getSimpleName();
    }
}