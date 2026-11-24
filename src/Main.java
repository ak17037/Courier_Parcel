import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    // all the data is stored in these lists (no database)
    static ArrayList<Customer> customers = new ArrayList<>();
    static ArrayList<Facility> facilities = new ArrayList<>();
    static ArrayList<Employee> employees = new ArrayList<>();
    static ArrayList<Vehicle> vehicles = new ArrayList<>();
    static ArrayList<Parcel> parcels = new ArrayList<>();
    static ArrayList<Complaint> complaints = new ArrayList<>();
    static ArrayList<Feedback> feedbacks = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadSampleData();

        System.out.println("=========================================");
        System.out.println("    COURIER & PARCEL MANAGEMENT SYSTEM   ");
        System.out.println("=========================================");

        if (!Login.login()) {
            System.out.println("Too many wrong tries. Program closed.");
            return;
        }

        while (true) {
            System.out.println("\n------ MAIN MENU (user: " + Login.currentUser + ") ------");
            System.out.println("1.  Customers");
            System.out.println("2.  Parcels");
            System.out.println("3.  Tracking & Delivery");
            System.out.println("4.  Vehicles");
            System.out.println("5.  Facilities");
            System.out.println("6.  Employees");
            System.out.println("7.  Payments");
            System.out.println("8.  Complaints");
            System.out.println("9.  Customer Feedback");
            System.out.println("10. Reports");
            System.out.println("0.  Logout & Exit");
            int choice = readInt("Enter choice: ");

            switch (choice) {
                case 1: CustomerMenu.show(); break;
                case 2: ParcelMenu.show(); break;
                case 3: TrackingMenu.show(); break;
                case 4: VehicleMenu.show(); break;
                case 5: FacilityMenu.show(); break;
                case 6: EmployeeMenu.show(); break;
                case 7: PaymentMenu.show(); break;
                case 8: ComplaintMenu.show(); break;
                case 9: FeedbackMenu.show(); break;
                case 10: ReportMenu.show(); break;
                case 0:
                    System.out.println("Logged out. Thank you, goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    static void loadSampleData() {
        customers.add(new Customer(1, "Arun Sharma", "9876543210", "Chandigarh"));
        customers.add(new Customer(2, "Priya Verma", "9876543211", "Ludhiana"));
        customers.add(new Customer(3, "Rahul Gupta", "9876543212", "Delhi"));
        customers.add(new Customer(4, "Sneha Patel", "9876543214", "Ahmedabad"));

        facilities.add(new Facility(1, "Chandigarh Hub", "Chandigarh"));
        facilities.add(new Facility(2, "Delhi Hub", "Delhi"));
        facilities.add(new Facility(3, "Ludhiana Branch", "Ludhiana"));
        facilities.add(new Facility(4, "Ahmedabad Branch", "Ahmedabad"));
        facilities.add(new Facility(5, "Sector 17 Branch", "Chandigarh"));

        employees.add(new Employee(1, "Rajesh Kumar", "Manager", "9812345670", facilities.get(0)));
        employees.add(new Employee(2, "Sunil Verma", "Delivery Agent", "9812345671", facilities.get(0)));
        employees.add(new Employee(3, "Rohit Jain", "Delivery Agent", "9812345672", facilities.get(1)));
        employees.add(new Employee(4, "Meera Devi", "Delivery Agent", "9812345673", facilities.get(2)));
        employees.add(new Employee(5, "Amit Thakur", "Delivery Agent", "9812345674", facilities.get(3)));
        employees.add(new Employee(6, "Kavita Rani", "Delivery Agent", "9812345675", facilities.get(4)));
        employees.add(new Employee(7, "Deepak Chauhan", "Driver", "9812345676", facilities.get(0)));

        vehicles.add(new Vehicle("PB10AB1234", "Tata Ace", 750));
        vehicles.add(new Vehicle("DL01CD5678", "Mahindra Bolero", 1200));
        vehicles.add(new Vehicle("GJ01GH3456", "Eicher Pro", 3000));
    }

    // ---------- search methods ----------

    static Customer findCustomer(int id) {
        for (Customer c : customers) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    static Facility findFacility(int id) {
        for (Facility f : facilities) {
            if (f.getId() == id) return f;
        }
        return null;
    }

    static Employee findEmployee(int id) {
        for (Employee e : employees) {
            if (e.getId() == id) return e;
        }
        return null;
    }

    static Vehicle findVehicle(String number) {
        for (Vehicle v : vehicles) {
            if (v.getNumber().equalsIgnoreCase(number)) return v;
        }
        return null;
    }

    static Parcel findParcel(String waybill) {
        for (Parcel p : parcels) {
            if (p.getWaybill().equalsIgnoreCase(waybill)) return p;
        }
        return null;
    }

    // ---------- input methods ----------

    static String readText(String msg) {
        System.out.print(msg);
        return sc.nextLine().trim();
    }

    static int readInt(String msg) {
        while (true) {
            try {
                return Integer.parseInt(readText(msg));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    static double readDouble(String msg) {
        while (true) {
            try {
                return Double.parseDouble(readText(msg));
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    static boolean readYesNo(String msg) {
        String answer = readText(msg + " (y/n): ");
        return answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes");
    }
}
