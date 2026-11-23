public class ComplaintMenu {

    static void show() {
        while (true) {
            System.out.println("\n------ COMPLAINTS ------");
            System.out.println("1. Register a complaint");
            System.out.println("2. View all complaints");
            System.out.println("3. View open complaints");
            System.out.println("4. Resolve a complaint");
            System.out.println("0. Back");
            int choice = Main.readInt("Enter choice: ");

            switch (choice) {
                case 1: register(); break;
                case 2: viewComplaints(false); break;
                case 3: viewComplaints(true); break;
                case 4: resolve(); break;
                case 0: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void register() {
        Parcel p = Main.findParcel(Main.readText("Waybill number: "));
        if (p == null) {
            System.out.println("Parcel not found.");
            return;
        }
        System.out.println("Problem: 1. Late Delivery  2. Damaged  3. Lost  4. Other");
        int t = Main.readInt("Choose problem: ");
        String type = "Other";
        if (t == 1) type = "Late Delivery";
        if (t == 2) type = "Damaged";
        if (t == 3) type = "Lost";
        String details = Main.readText("Describe the problem: ");

        int id = Main.complaints.size() + 1;
        Main.complaints.add(new Complaint(id, p, type, details));
        System.out.println("Complaint registered. Complaint ID = " + id);
    }

    static void viewComplaints(boolean onlyOpen) {
        int count = 0;
        for (Complaint c : Main.complaints) {
            if (onlyOpen && !c.getStatus().equals("Open")) continue;
            System.out.println("\nComplaint #" + c.getId() + " [" + c.getStatus() + "]");
            System.out.println("  Parcel  : " + c.getParcel().getWaybill() + " (" + c.getParcel().getSender().getName() + ")");
            System.out.println("  Problem : " + c.getType() + " - " + c.getDetails());
            System.out.println("  Reply   : " + c.getReply());
            count++;
        }
        if (count == 0) System.out.println("No complaints to show.");
    }

    static void resolve() {
        int id = Main.readInt("Complaint ID: ");
        Complaint found = null;
        for (Complaint c : Main.complaints) {
            if (c.getId() == id) found = c;
        }
        if (found == null) {
            System.out.println("Complaint not found.");
            return;
        }
        if (found.getStatus().equals("Resolved")) {
            System.out.println("This complaint is already resolved.");
            return;
        }
        String reply = Main.readText("Action taken / reply: ");
        found.resolve(reply);
        System.out.println("Complaint #" + id + " resolved.");
    }

    static int countOpen() {
        int count = 0;
        for (Complaint c : Main.complaints) {
            if (c.getStatus().equals("Open")) count++;
        }
        return count;
    }
}
