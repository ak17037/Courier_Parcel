// rating given by the sender after the parcel is delivered
public class Feedback {
    private Parcel parcel;
    private int rating;       // 1 to 5 stars
    private String comment;

    public Feedback(Parcel parcel, int rating, String comment) {
        this.parcel = parcel;
        this.rating = rating;
        this.comment = comment;
    }

    public Parcel getParcel() { return parcel; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
}
