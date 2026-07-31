public class Main {
    public static void main(String[] args) {
        Car myCar = new Car("ABC123", "Toyota Vios", 1500.0, 4);
        Van myVan = new Van("XYZ789", "Toyota Hiace", 2000.0, 1200);
        Motorcycle myMotorcycle = new Motorcycle("MNO456", "Honda Click", 500.0, 150);

        Vehicle[] vehicles = { myCar, myVan, myMotorcycle };

        for (Vehicle v : vehicles) {
            System.out.println("Plate: " + v.getPlateNumber());
            System.out.println("Model: " + v.getModel());
            System.out.println(v.getSpecificDetail());
            System.out.println("Rental cost for 3 days: " + v.calculateRentalCost(3));
            System.out.println("---");
        }
    }
}