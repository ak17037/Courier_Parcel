public class Employee {
    private int id;
    private String name;
    private String role;      // Manager, Delivery Agent, Driver or Office Staff
    private String phone;
    private Facility facility;

    public Employee(int id, String name, String role, String phone, Facility facility) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.phone = phone;
        this.facility = facility;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public String getPhone() { return phone; }
    public Facility getFacility() { return facility; }

    public void setFacility(Facility facility) { this.facility = facility; }
}
