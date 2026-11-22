public class EmployeeMenu {

    static void show() {
        while (true) {
            System.out.println("\n------ EMPLOYEES ------");
            System.out.println("1. Add employee");
            System.out.println("2. View all employees");
            System.out.println("3. Transfer employee to another facility");
            System.out.println("4. Remove employee");
            System.out.println("0. Back");
            int choice = Main.readInt("Enter choice: ");

            switch (choice) {
                case 1: addEmployee(); break;
                case 2: viewAll(); break;
                case 3: transfer(); break;
                case 4: removeEmployee(); break;
                case 0: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void addEmployee() {
        String name = Main.readText("Name  : ");
        if (!Validator.isValidName(name)) {
            System.out.println("Name can only have letters and spaces.");
            return;
        }
        String phone = Main.readText("Phone : ");
        if (!Validator.isValidPhone(phone)) {
            System.out.println("Phone number must be 10 digits.");
            return;
        }

        System.out.println("Role: 1. Manager  2. Delivery Agent  3. Driver  4. Office Staff");
        int r = Main.readInt("Choose role: ");
        String role = "Office Staff";
        if (r == 1) role = "Manager";
        if (r == 2) role = "Delivery Agent";
        if (r == 3) role = "Driver";

        FacilityMenu.viewAll();
        Facility f = Main.findFacility(Main.readInt("Facility ID: "));
        if (f == null) {
            System.out.println("Facility not found.");
            return;
        }

        // new ID is one more than the biggest ID used so far
        int id = 1;
        for (Employee e : Main.employees) {
            if (e.getId() >= id) id = e.getId() + 1;
        }
        Main.employees.add(new Employee(id, name, role, phone, f));
        System.out.println("Employee added. Employee ID = " + id);
    }

    static void viewAll() {
        System.out.println("\nID   Name                 Role            Phone        Facility");
        for (Employee e : Main.employees) {
            printEmployee(e);
        }
    }

    static void printEmployee(Employee e) {
        System.out.printf("%-4d %-20s %-15s %-12s %s%n", e.getId(), e.getName(), e.getRole(),
                e.getPhone(), e.getFacility().getName());
    }

    static void transfer() {
        Employee e = Main.findEmployee(Main.readInt("Employee ID: "));
        if (e == null) {
            System.out.println("Employee not found.");
            return;
        }
        FacilityMenu.viewAll();
        Facility f = Main.findFacility(Main.readInt("New facility ID: "));
        if (f == null) {
            System.out.println("Facility not found.");
            return;
        }
        e.setFacility(f);
        System.out.println(e.getName() + " now works at " + f.getName() + ".");
    }

    static void removeEmployee() {
        Employee e = Main.findEmployee(Main.readInt("Employee ID: "));
        if (e == null) {
            System.out.println("Employee not found.");
            return;
        }
        if (Main.readYesNo("Remove " + e.getName() + "?")) {
            Main.employees.remove(e);
            System.out.println("Employee removed.");
        }
    }
}
