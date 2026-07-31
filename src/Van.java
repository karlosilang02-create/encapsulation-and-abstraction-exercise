public class Van extends Vehicle {
    private int cargoCapacity; // in kg
    private static final double DRIVER_FEE = 500.0;

    public Van(String plateNumber, String model, double baseRate, int cargoCapacity) {
        super(plateNumber, model, baseRate);
        setCargoCapacity(cargoCapacity);
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public void setCargoCapacity(int cargoCapacity) {
        if (cargoCapacity <= 0) {
            throw new IllegalArgumentException("Cargo capacity must be strictly greater than 0 kg.");
        }
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public double calculateRentalCost(int days) {
        if (days <= 0) {
            throw new IllegalArgumentException("Rental days must be greater than 0.");
        }
        return (getBaseRate() * days) + DRIVER_FEE;
    }

    @Override
    public String getSpecificDetail() {
        return "Cargo: " + cargoCapacity + "kg";
    }
}