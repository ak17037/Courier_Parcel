import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ReportMenu {

    static void show() {
        while (true) {
            System.out.println("\n------ REPORTS ------");
            System.out.println("1. Summary report");
            System.out.println("2. Delayed parcels");
            System.out.println("3. Customer-wise report");
            System.out.println("4. Income by service type");
            System.out.println("5. Save parcel list to a file");
            System.out.println("0. Back");
            int choice = Main.readInt("Enter choice: ");

            switch (choice) {
                case 1: summary(); break;
                case 2: delayedParcels(); break;
                case 3: customerReport(); break;
                case 4: serviceIncome(); break;
                case 5: saveToFile(); break;
                case 0: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void summary() {
        double collected = 0;
        double pending = 0;
        for (Parcel p : Main.parcels) {
            if (p.getStatus().equals("CANCELLED")) continue;
            if (p.isPaid()) {
                collected = collected + p.getAmount();
            } else {
                pending = pending + p.getAmount();
            }
        }
        System.out.printf("\nMoney collected : Rs %.2f%n", collected);
        System.out.printf("Money pending   : Rs %.2f%n", pending);

        System.out.println("\nParcels by status:");
        String[] statuses = {"BOOKED", "IN_TRANSIT", "ARRIVED", "OUT_FOR_DELIVERY", "DELIVERED", "RETURNED", "CANCELLED"};
        for (String s : statuses) {
            int count = 0;
            for (Parcel p : Main.parcels) {
                if (p.getStatus().equals(s)) count++;
            }
            System.out.printf("  %-17s %d%n", s, count);
        }
        System.out.println("Open complaints : " + ComplaintMenu.countOpen());
    }

    static void delayedParcels() {
        int count = 0;
        for (Parcel p : Main.parcels) {
            if (p.isDelayed()) {
                System.out.println("  " + p.getWaybill() + "  expected " + p.getExpectedDate() + "  now " + p.getStatus());
                count++;
            }
        }
        if (count == 0) System.out.println("No delayed parcels. All on time!");
    }

    static void customerReport() {
        System.out.println("\nCustomer             Parcels Sent  Total Amount");
        for (Customer c : Main.customers) {
            int count = 0;
            double total = 0;
            for (Parcel p : Main.parcels) {
                if (p.getSender() == c && !p.getStatus().equals("CANCELLED")) {
                    count++;
                    total = total + p.getAmount();
                }
            }
            if (count > 0) System.out.printf("%-20s %-13d Rs %.2f%n", c.getName(), count, total);
        }
    }

    static void serviceIncome() {
        String[] services = {"Express", "Standard", "Economy"};
        System.out.println("\nService    Parcels  Income");
        for (String s : services) {
            int count = 0;
            double income = 0;
            for (Parcel p : Main.parcels) {
                if (p.getService().equals(s) && !p.getStatus().equals("CANCELLED")) {
                    count++;
                    income = income + p.getAmount();
                }
            }
            System.out.printf("%-10s %-8d Rs %.2f%n", s, count, income);
        }
    }

    static void saveToFile() {
        String fileName = "parcels_report.txt";
        try (PrintWriter out = new PrintWriter(new FileWriter(fileName))) {
            out.println("Courier & Parcel Management System - Parcel Report");
            out.println("Waybill  Sender          Receiver        Weight  Amount     Payment    Status");
            for (Parcel p : Main.parcels) {
                out.printf("%-8s %-15s %-15s %-7.1f %-10.2f %-10s %s%n", p.getWaybill(), p.getSender().getName(),
                        p.getReceiver().getName(), p.getWeight(), p.getAmount(), p.getPaymentStatus(), p.getStatus());
            }
            System.out.println("Report saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Could not save the file: " + e.getMessage());
        }
    }
}
