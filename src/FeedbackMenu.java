public class FeedbackMenu {

    static void show() {
        while (true) {
            System.out.println("\n------ CUSTOMER FEEDBACK ------");
            System.out.println("1. Give feedback for a delivered parcel");
            System.out.println("2. View all feedback");
            System.out.println("3. Average rating");
            System.out.println("0. Back");
            int choice = Main.readInt("Enter choice: ");

            switch (choice) {
                case 1: giveFeedback(); break;
                case 2: viewAll(); break;
                case 3: averageRating(); break;
                case 0: return;
                default: System.out.println("Invalid choice.");
            }
        }
    }

    static void giveFeedback() {
        Parcel p = Main.findParcel(Main.readText("Waybill number: "));
        if (p == null) {
            System.out.println("Parcel not found.");
            return;
        }
        if (!p.getStatus().equals("DELIVERED")) {
            System.out.println("Feedback can be given only after delivery.");
            return;
        }
        for (Feedback f : Main.feedbacks) {
            if (f.getParcel() == p) {
                System.out.println("Feedback for this parcel is already given.");
                return;
            }
        }

        int rating = Main.readInt("Rating (1 to 5): ");
        if (rating < 1 || rating > 5) {
            System.out.println("Rating must be between 1 and 5.");
            return;
        }
        String comment = Main.readText("Comment: ");
        Main.feedbacks.add(new Feedback(p, rating, comment));
        System.out.println("Thank you for your feedback!");
    }

    static void viewAll() {
        if (Main.feedbacks.isEmpty()) {
            System.out.println("No feedback yet.");
            return;
        }
        for (Feedback f : Main.feedbacks) {
            String stars = "*".repeat(f.getRating());
            System.out.printf("%-8s %-15s %-6s %s%n", f.getParcel().getWaybill(),
                    f.getParcel().getSender().getName(), stars, f.getComment());
        }
    }

    static void averageRating() {
        if (Main.feedbacks.isEmpty()) {
            System.out.println("No feedback yet.");
            return;
        }
        int total = 0;
        for (Feedback f : Main.feedbacks) {
            total = total + f.getRating();
        }
        double average = (double) total / Main.feedbacks.size();
        System.out.printf("Average rating: %.1f out of 5 (from %d feedback)%n", average, Main.feedbacks.size());
    }
}
