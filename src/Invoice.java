public class Invoice {

    static void print(Parcel p) {
        System.out.println("\n================ INVOICE ================");
        System.out.println("Waybill  : " + p.getWaybill());
        System.out.println("Date     : " + p.getBookingDate());
        System.out.println("Sender   : " + p.getSender().getName() + ", " + p.getSender().getPhone());
        System.out.println("Receiver : " + p.getReceiver().getName() + ", " + p.getReceiver().getPhone());
        System.out.println("Route    : " + p.getFrom().getName() + " -> " + p.getTo().getName());
        System.out.println("Service  : " + p.getService() + " (expected by " + p.getExpectedDate() + ")");
        System.out.println("-----------------------------------------");
        PriceCalculator.showBill(p.getWeight(), p.getService(), p.getFrom(), p.getTo(), p.getItemValue());
        System.out.println("-----------------------------------------");
        System.out.println("Payment  : " + p.getPaymentMethod() + " - " + p.getPaymentStatus());
        System.out.println("=========================================");
    }
}
