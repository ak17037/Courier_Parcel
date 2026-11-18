public class Customer {
    private int id;
    private String name;
    private String phone;
    private String city;

    public Customer(int id, String name, String phone, String city) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.city = city;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getCity() { return city; }
}
