public class Vehicle {
    private String number;
    private String model;
    private double capacity;   // in kg
    private String status;     // "Available" or "Maintenance"

    public Vehicle(String number, String model, double capacity) {
        this.number = number;
        this.model = model;
        this.capacity = capacity;
        this.status = "Available";
    }

    public String getNumber() { return number; }
    public String getModel() { return model; }
    public double getCapacity() { return capacity; }
    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
