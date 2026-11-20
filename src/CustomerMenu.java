public class CustomerMenu {

    static void show() {
        while (true) {
            System.out.println("\n------ CUSTOMERS ------");
            System.out.println("1. Add customer");
            System.out.println("2. View all customers");
            System.out.println("3. Search customer by name");
            System.out.println("4. View a customer's parcels");
            System.out.println("0. Back");
            int choice = Main.readInt("Enter choice: ");

            switch (choice) {
                case 1: addCustomer(); break;
                case 2: viewAll(); break;
                case 3: search(); break;
                case 4: customerParcels(); break;
                case 0: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void addCustomer() {
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
        String city = Main.readText("City  : ");

        int id = Main.customers.size() + 1;
        Main.customers.add(new Customer(id, name, phone, city));
        System.out.println("Customer added. Customer ID = " + id);
    }

    static void viewAll() {
        System.out.println("\nID   Name                 Phone        City");
        for (Customer c : Main.customers) {
            printCustomer(c);
        }
    }

    static void search() {
        String text = Main.readText("Enter name to search: ").toLowerCase();
        boolean found = false;
        for (Customer c : Main.customers) {
            if (c.getName().toLowerCase().contains(text)) {
                printCustomer(c);
                found = true;
            }
        }
        if (!found) System.out.println("No customer found.");
    }

    static void customerParcels() {
        Customer c = Main.findCustomer(Main.readInt("Customer ID: "));
        if (c == null) {
            System.out.println("Customer not found.");
            return;
        }
        System.out.println("\nParcels of " + c.getName() + ":");
        boolean found = false;
        for (Parcel p : Main.parcels) {
            if (p.getSender() == c) {
                System.out.println("  Sent     " + p.getWaybill() + " to " + p.getReceiver().getName() + " - " + p.getStatus());
                found = true;
            } else if (p.getReceiver() == c) {
                System.out.println("  Received " + p.getWaybill() + " from " + p.getSender().getName() + " - " + p.getStatus());
                found = true;
            }
        }
        if (!found) System.out.println("  No parcels.");
    }

    static void printCustomer(Customer c) {
        System.out.printf("%-4d %-20s %-12s %s%n", c.getId(), c.getName(), c.getPhone(), c.getCity());
    }
}
