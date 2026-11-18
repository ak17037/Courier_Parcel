// A hub or branch office of the courier company
public class Facility {
    private int id;
    private String name;
    private String city;

    public Facility(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getCity() { return city; }
}
