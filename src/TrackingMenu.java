// Parcel journey: BOOKED -> IN_TRANSIT -> ARRIVED -> OUT_FOR_DELIVERY -> DELIVERED (or RETURNED)
public class TrackingMenu {

    static void show() {
        while (true) {
            System.out.println("\n------ TRACKING & DELIVERY ------");
            System.out.println("1. Track a parcel");
            System.out.println("2. Dispatch parcel on a vehicle");
            System.out.println("3. Mark parcel arrived at destination");
            System.out.println("4. Send parcel out for delivery");
            System.out.println("5. Mark parcel delivered");
            System.out.println("6. Delivery failed (return to sender)");
            System.out.println("0. Back");
            int choice = Main.readInt("Enter choice: ");

            switch (choice) {
                case 1: track(); break;
                case 2: dispatch(); break;
                case 3: arrived(); break;
                case 4: outForDelivery(); break;
                case 5: delivered(); break;
                case 6: returnToSender(); break;
                case 0: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void track() {
        Parcel p = Main.findParcel(Main.readText("Waybill number: "));
        if (p == null) {
            System.out.println("Parcel not found.");
            return;
        }
        System.out.println("\nWaybill  : " + p.getWaybill());
        System.out.println("From     : " + p.getSender().getName() + " (" + p.getFrom().getName() + ")");
        System.out.println("To       : " + p.getReceiver().getName() + " (" + p.getTo().getName() + ")");
        System.out.println("Weight   : " + p.getWeight() + " kg, " + p.getService());
        System.out.printf("Amount   : Rs %.2f (%s, %s)%n", p.getAmount(), p.getPaymentMethod(), p.getPaymentStatus());
        System.out.println("Booked   : " + p.getBookingDate() + ", expected by " + p.getExpectedDate());
        System.out.println("Status   : " + p.getStatus());
        if (p.isDelayed()) System.out.println("NOTE     : This parcel is DELAYED!");
        System.out.println("History  :");
        for (String h : p.getHistory()) {
            System.out.println("   " + h);
        }
    }

    // asks for a waybill and checks the parcel is in the needed status
    static Parcel askParcel(String neededStatus) {
        Parcel p = Main.findParcel(Main.readText("Waybill number: "));
        if (p == null) {
            System.out.println("Parcel not found.");
            return null;
        }
        if (!p.getStatus().equals(neededStatus)) {
            System.out.println("Parcel must be " + neededStatus + " for this. Current status: " + p.getStatus());
            return null;
        }
        return p;
    }

    static void dispatch() {
        Parcel p = askParcel("BOOKED");
        if (p == null) return;

        VehicleMenu.viewAll();
        Vehicle v = Main.findVehicle(Main.readText("Vehicle number: "));
        if (v == null) {
            System.out.println("Vehicle not found.");
            return;
        }
        if (!v.getStatus().equals("Available")) {
            System.out.println("This vehicle is under maintenance.");
            return;
        }
        double load = VehicleMenu.currentLoad(v);
        if (load + p.getWeight() > v.getCapacity()) {
            System.out.println("Vehicle is full. Space left: " + (v.getCapacity() - load) + " kg");
            return;
        }

        p.setVehicleNumber(v.getNumber());
        p.setStatus("IN_TRANSIT", "Left " + p.getFrom().getName() + " on vehicle " + v.getNumber());
        System.out.println("Parcel dispatched.");
    }

    static void arrived() {
        Parcel p = askParcel("IN_TRANSIT");
        if (p == null) return;
        p.setStatus("ARRIVED", "Reached " + p.getTo().getName());
        System.out.println("Parcel arrived at " + p.getTo().getName() + ".");
    }

    static void outForDelivery() {
        Parcel p = askParcel("ARRIVED");
        if (p == null) return;

        // only delivery agents of the destination facility can take it
        System.out.println("Delivery agents at " + p.getTo().getName() + ":");
        boolean found = false;
        for (Employee e : Main.employees) {
            if (e.getRole().equals("Delivery Agent") && e.getFacility() == p.getTo()) {
                System.out.println("  " + e.getId() + ". " + e.getName());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No delivery agent here. Add one in the Employees menu first.");
            return;
        }

        Employee agent = Main.findEmployee(Main.readInt("Delivery agent ID: "));
        if (agent == null || !agent.getRole().equals("Delivery Agent") || agent.getFacility() != p.getTo()) {
            System.out.println("Please choose an agent from the list.");
            return;
        }
        p.setStatus("OUT_FOR_DELIVERY", "With delivery agent " + agent.getName());
        System.out.println("Parcel is out for delivery with " + agent.getName() + ".");
    }

    static void delivered() {
        Parcel p = askParcel("OUT_FOR_DELIVERY");
        if (p == null) return;

        // parcel cannot be handed over until it is paid
        if (!p.isPaid()) {
            System.out.printf("Payment of Rs %.2f is pending.%n", p.getAmount());
            if (!Main.readYesNo("Collect cash now?")) {
                System.out.println("Cannot deliver without payment.");
                return;
            }
            p.setPaid(true);
            p.setPaymentMethod("Cash");
        }
        p.setStatus("DELIVERED", "Delivered to " + p.getReceiver().getName());
        System.out.println("Parcel delivered.");
    }

    static void returnToSender() {
        Parcel p = askParcel("OUT_FOR_DELIVERY");
        if (p == null) return;
        String reason = Main.readText("Reason (e.g. receiver not at home): ");
        p.setStatus("RETURNED", "Returned to " + p.getSender().getName() + ": " + reason);
        System.out.println("Parcel will be returned to the sender.");
    }
}
