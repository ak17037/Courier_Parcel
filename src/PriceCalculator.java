// all the pricing rules of the courier company are kept here
public class PriceCalculator {

    static final double GST_PERCENT = 18;
    static final double INSURANCE_PERCENT = 2;
    static final double OTHER_CITY_CHARGE = 50;

    static double ratePerKg(String service) {
        if (service.equals("Express")) return 100;
        if (service.equals("Economy")) return 30;
        return 50;   // Standard
    }

    static int deliveryDays(String service) {
        if (service.equals("Express")) return 1;
        if (service.equals("Economy")) return 5;
        return 3;    // Standard
    }

    static String chooseService() {
        System.out.println("Service: 1. Express (Rs 100/kg, 1 day)");
        System.out.println("         2. Standard (Rs 50/kg, 3 days)");
        System.out.println("         3. Economy (Rs 30/kg, 5 days)");
        int s = Main.readInt("Choose service: ");
        if (s == 1) return "Express";
        if (s == 3) return "Economy";
        return "Standard";
    }

    // prints the bill and returns the total amount
    static double showBill(double weight, String service, Facility from, Facility to, double itemValue) {
        double chargeWeight = weight;
        if (chargeWeight < 1) chargeWeight = 1;   // minimum charge is for 1 kg

        double freight = chargeWeight * ratePerKg(service);
        double cityCharge = 0;
        if (!from.getCity().equalsIgnoreCase(to.getCity())) cityCharge = OTHER_CITY_CHARGE;
        double insurance = itemValue * INSURANCE_PERCENT / 100;
        double subTotal = freight + cityCharge + insurance;
        double gst = subTotal * GST_PERCENT / 100;
        double total = subTotal + gst;

        System.out.printf("Freight (%.1f kg x Rs %.0f) : Rs %.2f%n", chargeWeight, ratePerKg(service), freight);
        System.out.printf("Other city charge       : Rs %.2f%n", cityCharge);
        System.out.printf("Insurance (2%%)          : Rs %.2f%n", insurance);
        System.out.printf("GST (18%%)               : Rs %.2f%n", gst);
        System.out.printf("TOTAL                   : Rs %.2f%n", total);
        return total;
    }
}
