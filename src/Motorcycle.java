public class Motorcycle extends Vehicle {
    private int engineDisplacement; // in cc

    public Motorcycle(String plateNumber, String model, double baseRate, int engineDisplacement) {
        super(plateNumber, model, baseRate);
        setEngineDisplacement(engineDisplacement);
    }

    public int getEngineDisplacement() {
        return engineDisplacement;
    }

    public void setEngineDisplacement(int engineDisplacement) {
        if (engineDisplacement <= 0) {
            throw new IllegalArgumentException("Engine displacement must be strictly greater than 0 cc.");
        }
        this.engineDisplacement = engineDisplacement;
    }

    @Override
    public double calculateRentalCost(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Rental days must be greater than 0.");
        }
        return getBaseRate() * days;
    }

    @Override
    public String getSpecificDetail() {
        return "Displacement: " + engineDisplacement + "cc";
    }
}