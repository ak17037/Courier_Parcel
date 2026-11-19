public class VehicleMenu {

    static void show() {
        while (true) {
            System.out.println("\n------ VEHICLES ------");
            System.out.println("1. Add vehicle");
            System.out.println("2. View all vehicles");
            System.out.println("3. Change vehicle status");
            System.out.println("4. Parcels on a vehicle");
            System.out.println("0. Back");
            int choice = Main.readInt("Enter choice: ");

            switch (choice) {
                case 1: addVehicle(); break;
                case 2: viewAll(); break;
                case 3: changeStatus(); break;
                case 4: parcelsOnVehicle(); break;
                case 0: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void addVehicle() {
        String number = Main.readText("Vehicle number : ").toUpperCase();
        if (!Validator.isValidVehicleNumber(number)) {
            System.out.println("Vehicle number should look like PB10AB1234.");
            return;
        }
        if (Main.findVehicle(number) != null) {
            System.out.println("This vehicle already exists.");
            return;
        }
        String model = Main.readText("Model          : ");
        double capacity = Main.readDouble("Capacity (kg)  : ");
        if (capacity <= 0) {
            System.out.println("Capacity must be more than 0.");
            return;
        }
        Main.vehicles.add(new Vehicle(number, model, capacity));
        System.out.println("Vehicle added.");
    }

    static void viewAll() {
        System.out.println("\nNumber       Model              Capacity  Load   Status");
        for (Vehicle v : Main.vehicles) {
            System.out.printf("%-12s %-18s %-9.0f %-6.0f %s%n", v.getNumber(), v.getModel(),
                    v.getCapacity(), currentLoad(v), v.getStatus());
        }
    }

    // total weight of the parcels travelling on this vehicle right now
    static double currentLoad(Vehicle v) {
        double load = 0;
        for (Parcel p : Main.parcels) {
            if (p.getStatus().equals("IN_TRANSIT") && v.getNumber().equals(p.getVehicleNumber())) {
                load = load + p.getWeight();
            }
        }
        return load;
    }

    static void changeStatus() {
        Vehicle v = Main.findVehicle(Main.readText("Vehicle number: "));
        if (v == null) {
            System.out.println("Vehicle not found.");
            return;
        }
        if (v.getStatus().equals("Available")) {
            if (currentLoad(v) > 0) {
                System.out.println("Vehicle is carrying parcels. It cannot go for maintenance now.");
                return;
            }
            v.setStatus("Maintenance");
        } else {
            v.setStatus("Available");
        }
        System.out.println("Vehicle " + v.getNumber() + " is now " + v.getStatus() + ".");
    }

    static void parcelsOnVehicle() {
        Vehicle v = Main.findVehicle(Main.readText("Vehicle number: "));
        if (v == null) {
            System.out.println("Vehicle not found.");
            return;
        }
        int count = 0;
        for (Parcel p : Main.parcels) {
            if (p.getStatus().equals("IN_TRANSIT") && v.getNumber().equals(p.getVehicleNumber())) {
                System.out.println("  " + p.getWaybill() + "  " + p.getWeight() + " kg  to " + p.getTo().getName());
                count++;
            }
        }
        System.out.println(count + " parcel(s) on " + v.getNumber()
                + ". Space left: " + (v.getCapacity() - currentLoad(v)) + " kg");
    }
}
