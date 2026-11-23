public class PaymentMenu {

    static void show() {
        while (true) {
            System.out.println("\n------ PAYMENTS ------");
            System.out.println("1. View all payments");
            System.out.println("2. Collect a pending payment");
            System.out.println("3. Print invoice");
            System.out.println("0. Back");
            int choice = Main.readInt("Enter choice: ");

            switch (choice) {
                case 1: viewAll(); break;
                case 2: collect(); break;
                case 3: printInvoice(); break;
                case 0: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static String choosePaymentMethod() {
        System.out.println("Payment method: 1. Cash  2. UPI  3. Card");
        int m = Main.readInt("Choose method: ");
        if (m == 2) return "UPI";
        if (m == 3) return "Card";
        return "Cash";
    }

    static void viewAll() {
        if (Main.parcels.isEmpty()) {
            System.out.println("No payments yet.");
            return;
        }
        System.out.println("\nWaybill  Sender          Amount     Method  Payment");
        for (Parcel p : Main.parcels) {
            System.out.printf("%-8s %-15s %-10.2f %-7s %s%n", p.getWaybill(), p.getSender().getName(),
                    p.getAmount(), p.getPaymentMethod(), p.getPaymentStatus());
        }
    }

    static void collect() {
        Parcel p = Main.findParcel(Main.readText("Waybill number: "));
        if (p == null) {
            System.out.println("Parcel not found.");
            return;
        }
        if (p.getStatus().equals("CANCELLED")) {
            System.out.println("This parcel is cancelled.");
            return;
        }
        if (p.isPaid()) {
            System.out.println("This parcel is already paid.");
            return;
        }
        String method = choosePaymentMethod();
        p.setPaymentMethod(method);
        p.setPaid(true);
        System.out.printf("Rs %.2f received by %s.%n", p.getAmount(), method);
    }

    static void printInvoice() {
        Parcel p = Main.findParcel(Main.readText("Waybill number: "));
        if (p == null) {
            System.out.println("Parcel not found.");
            return;
        }
        Invoice.print(p);
    }
}
