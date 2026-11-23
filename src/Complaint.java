public class Complaint {
    private int id;
    private Parcel parcel;
    private String type;      // Late Delivery, Damaged, Lost or Other
    private String details;
    private String status;    // Open or Resolved
    private String reply;

    public Complaint(int id, Parcel parcel, String type, String details) {
        this.id = id;
        this.parcel = parcel;
        this.type = type;
        this.details = details;
        this.status = "Open";
        this.reply = "-";
    }

    public int getId() { return id; }
    public Parcel getParcel() { return parcel; }
    public String getType() { return type; }
    public String getDetails() { return details; }
    public String getStatus() { return status; }
    public String getReply() { return reply; }

    public void resolve(String reply) {
        this.status = "Resolved";
        this.reply = reply;
    }
}
