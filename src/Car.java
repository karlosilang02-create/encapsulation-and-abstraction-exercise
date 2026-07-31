public class Car extends Vehicle {
    private int numberOfSeats;

    public Car(String plateNumber, String model, double baseRate, int numberOfSeats) {
        super(plateNumber, model, baseRate);
        setNumberOfSeats(numberOfSeats);
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        if (numberOfSeats <= 0) {
            throw new IllegalArgumentException("Number of seats must be strictly greater than 0.");
        }
        this.numberOfSeats = numberOfSeats;
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
        return "Seats: " + numberOfSeats;
    }
}