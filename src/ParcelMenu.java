public class ParcelMenu {

    static int nextWaybill = 1001;

    static void show() {
        while (true) {
            System.out.println("\n------ PARCELS ------");
            System.out.println("1. Book a new parcel");
            System.out.println("2. Get a price quote");
            System.out.println("3. View all parcels");
            System.out.println("4. Cancel a parcel");
            System.out.println("0. Back");
            int choice = Main.readInt("Enter choice: ");

            switch (choice) {
                case 1: bookParcel(); break;
                case 2: priceQuote(); break;
                case 3: viewAll(); break;
                case 4: cancelParcel(); break;
                case 0: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void bookParcel() {
        Customer sender = Main.findCustomer(Main.readInt("Sender customer ID   : "));
        Customer receiver = Main.findCustomer(Main.readInt("Receiver customer ID : "));
        if (sender == null || receiver == null) {
            System.out.println("Customer not found.");
            return;
        }
        if (sender == receiver) {
            System.out.println("Sender and receiver cannot be the same.");
            return;
        }

        FacilityMenu.viewAll();
        Facility from = Main.findFacility(Main.readInt("From facility ID : "));
        Facility to = Main.findFacility(Main.readInt("To facility ID   : "));
        if (from == null || to == null) {
            System.out.println("Facility not found.");
            return;
        }

        double weight = Main.readDouble("Weight (kg) : ");
        if (weight <= 0) {
            System.out.println("Weight must be more than 0.");
            return;
        }
        String service = PriceCalculator.chooseService();
        double itemValue = Main.readDouble("Item value for insurance (0 = no insurance): ");
        if (itemValue < 0) {
            System.out.println("Item value cannot be negative.");
            return;
        }

        System.out.println();
        double total = PriceCalculator.showBill(weight, service, from, to, itemValue);
        if (!Main.readYesNo("Confirm booking?")) {
            System.out.println("Booking cancelled.");
            return;
        }

        String method = PaymentMenu.choosePaymentMethod();
        boolean paid = Main.readYesNo("Paid now?");

        String waybill = "WB" + nextWaybill;
        nextWaybill++;
        Parcel p = new Parcel(waybill, sender, receiver, from, to, weight, service, itemValue, total, method, paid);
        Main.parcels.add(p);
        System.out.println("Parcel booked! Waybill number: " + waybill);
        System.out.println("Expected delivery date: " + p.getExpectedDate());

        if (Main.readYesNo("Print invoice?")) Invoice.print(p);
    }

    // shows the price without booking anything
    static void priceQuote() {
        FacilityMenu.viewAll();
        Facility from = Main.findFacility(Main.readInt("From facility ID : "));
        Facility to = Main.findFacility(Main.readInt("To facility ID   : "));
        if (from == null || to == null) {
            System.out.println("Facility not found.");
            return;
        }
        double weight = Main.readDouble("Weight (kg) : ");
        if (weight <= 0) {
            System.out.println("Weight must be more than 0.");
            return;
        }
        String service = PriceCalculator.chooseService();

        System.out.println();
        PriceCalculator.showBill(weight, service, from, to, 0);
        System.out.println("Delivery in " + PriceCalculator.deliveryDays(service) + " day(s).");
    }

    static void viewAll() {
        if (Main.parcels.isEmpty()) {
            System.out.println("No parcels booked yet.");
            return;
        }
        System.out.println("\nWaybill  Sender          Receiver        Weight  Amount     Status");
        for (Parcel p : Main.parcels) {
            System.out.printf("%-8s %-15s %-15s %-7.1f %-10.2f %s%n", p.getWaybill(), p.getSender().getName(),
                    p.getReceiver().getName(), p.getWeight(), p.getAmount(), p.getStatus());
        }
    }

    static void cancelParcel() {
        Parcel p = Main.findParcel(Main.readText("Waybill number: "));
        if (p == null) {
            System.out.println("Parcel not found.");
            return;
        }
        if (!p.getStatus().equals("BOOKED")) {
            System.out.println("Only BOOKED parcels can be cancelled. Current status: " + p.getStatus());
            return;
        }
        if (!Main.readYesNo("Cancel parcel " + p.getWaybill() + "?")) return;

        if (p.isPaid()) {
            System.out.printf("Refund of Rs %.2f given to %s.%n", p.getAmount(), p.getSender().getName());
            p.setStatus("CANCELLED", "Cancelled by customer, money refunded");
        } else {
            p.setStatus("CANCELLED", "Cancelled by customer");
        }
        System.out.println("Parcel " + p.getWaybill() + " cancelled.");
    }
}
