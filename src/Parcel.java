import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parcel {
    private String waybill;
    private Customer sender;
    private Customer receiver;
    private Facility from;
    private Facility to;
    private double weight;
    private String service;        // Express, Standard or Economy
    private double itemValue;      // value of the item for insurance (0 = no insurance)
    private double amount;
    private String paymentMethod;  // Cash, UPI or Card
    private boolean paid;
    private String status;         // BOOKED, IN_TRANSIT, ARRIVED, OUT_FOR_DELIVERY, DELIVERED, RETURNED, CANCELLED
    private String vehicleNumber;
    private LocalDate bookingDate;
    private LocalDate expectedDate;
    private ArrayList<String> history = new ArrayList<>();

    public Parcel(String waybill, Customer sender, Customer receiver, Facility from, Facility to, double weight,
                  String service, double itemValue, double amount, String paymentMethod, boolean paid) {
        this.waybill = waybill;
        this.sender = sender;
        this.receiver = receiver;
        this.from = from;
        this.to = to;
        this.weight = weight;
        this.service = service;
        this.itemValue = itemValue;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paid = paid;
        this.bookingDate = LocalDate.now();
        this.expectedDate = bookingDate.plusDays(PriceCalculator.deliveryDays(service));
        setStatus("BOOKED", "Booked at " + from.getName());
    }

    // changes the status and saves it in the tracking history
    public void setStatus(String status, String remark) {
        this.status = status;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        history.add(LocalDateTime.now().format(fmt) + " | " + status + " | " + remark);
    }

    // a parcel is late if it is still on the way after the expected date
    public boolean isDelayed() {
        if (status.equals("DELIVERED") || status.equals("RETURNED") || status.equals("CANCELLED")) return false;
        return LocalDate.now().isAfter(expectedDate);
    }

    public String getPaymentStatus() {
        if (status.equals("CANCELLED") && paid) return "Refunded";
        if (status.equals("CANCELLED")) return "Cancelled";
        if (paid) return "Paid";
        return "Pending";
    }

    public String getWaybill() { return waybill; }
    public Customer getSender() { return sender; }
    public Customer getReceiver() { return receiver; }
    public Facility getFrom() { return from; }
    public Facility getTo() { return to; }
    public double getWeight() { return weight; }
    public String getService() { return service; }
    public double getItemValue() { return itemValue; }
    public double getAmount() { return amount; }
    public String getPaymentMethod() { return paymentMethod; }
    public boolean isPaid() { return paid; }
    public String getStatus() { return status; }
    public String getVehicleNumber() { return vehicleNumber; }
    public LocalDate getBookingDate() { return bookingDate; }
    public LocalDate getExpectedDate() { return expectedDate; }
    public ArrayList<String> getHistory() { return history; }

    public void setPaid(boolean paid) { this.paid = paid; }
    public void setPaymentMethod(String method) { this.paymentMethod = method; }
    public void setVehicleNumber(String number) { this.vehicleNumber = number; }
}
