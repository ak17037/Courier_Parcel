public class FacilityMenu {

    static void show() {
        while (true) {
            System.out.println("\n------ FACILITIES ------");
            System.out.println("1. Add facility");
            System.out.println("2. View all facilities");
            System.out.println("3. Parcels at a facility");
            System.out.println("4. Employees at a facility");
            System.out.println("0. Back");
            int choice = Main.readInt("Enter choice: ");

            switch (choice) {
                case 1: addFacility(); break;
                case 2: viewAll(); break;
                case 3: parcelsAt(); break;
                case 4: employeesAt(); break;
                case 0: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void addFacility() {
        String name = Main.readText("Facility name : ");
        String city = Main.readText("City          : ");
        if (name.isEmpty() || city.isEmpty()) {
            System.out.println("Name and city cannot be empty.");
            return;
        }
        int id = Main.facilities.size() + 1;
        Main.facilities.add(new Facility(id, name, city));
        System.out.println("Facility added. Facility ID = " + id);
    }

    static void viewAll() {
        System.out.println("\nID   Facility Name        City");
        for (Facility f : Main.facilities) {
            System.out.printf("%-4d %-20s %s%n", f.getId(), f.getName(), f.getCity());
        }
    }

    // a parcel is inside a facility when it is booked there or has arrived there
    static void parcelsAt() {
        Facility f = Main.findFacility(Main.readInt("Facility ID: "));
        if (f == null) {
            System.out.println("Facility not found.");
            return;
        }
        int count = 0;
        for (Parcel p : Main.parcels) {
            boolean waitingToGo = p.getStatus().equals("BOOKED") && p.getFrom() == f;
            boolean reachedHere = p.getStatus().equals("ARRIVED") && p.getTo() == f;
            if (waitingToGo || reachedHere) {
                System.out.println("  " + p.getWaybill() + "  " + p.getWeight() + " kg  " + p.getStatus());
                count++;
            }
        }
        System.out.println(count + " parcel(s) at " + f.getName() + ".");
    }

    static void employeesAt() {
        Facility f = Main.findFacility(Main.readInt("Facility ID: "));
        if (f == null) {
            System.out.println("Facility not found.");
            return;
        }
        System.out.println("\nEmployees at " + f.getName() + ":");
        for (Employee e : Main.employees) {
            if (e.getFacility() == f) EmployeeMenu.printEmployee(e);
        }
    }
}
