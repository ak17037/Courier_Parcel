# Courier & Parcel Management System

**Course:** 25CSE0202 – Programming in Java
**Institution:** Chitkara University
**Evaluation:** CE-1

A simple terminal program for a courier company. Staff log in, add customers, book parcels, send them on a vehicle, track them, deliver them, collect payment, handle complaints and feedback, and see reports. There is no database: all data is kept in `ArrayList`s while the program runs, and some sample customers, facilities, employees and vehicles are loaded at the start.

## How to run

You need JDK 11 or newer.

```cmd
scripts\compile.bat
scripts\run.bat
```

Or by hand:

```cmd
javac -d out src\*.java
java -cp out Main
```

**Login:** username `admin`, password `admin123` (or `staff` / `staff123`). You get 3 tries.

## Files (22)

### Data classes

| File | What it stores |
|---|---|
| `Customer.java` | ID, name, phone, city |
| `Facility.java` | A hub or branch office: ID, name, city |
| `Employee.java` | ID, name, role (Manager, Delivery Agent, Driver, Office Staff), phone, facility |
| `Vehicle.java` | Number, model, capacity in kg, status (Available / Maintenance) |
| `Parcel.java` | Sender, receiver, route, weight, price, payment, status, booking and expected date, tracking history |
| `Complaint.java` | A problem with a parcel (late, damaged, lost) and the reply given |
| `Feedback.java` | 1 to 5 star rating and comment for a delivered parcel |

### Helper classes

| File | What it does |
|---|---|
| `Main.java` | Starts the program, login, main menu, keeps all the lists, search and input methods |
| `Login.java` | Checks username and password, 3 tries |
| `Validator.java` | Checks phone number, name and vehicle number |
| `PriceCalculator.java` | All pricing rules and the bill |
| `Invoice.java` | Prints the invoice of a parcel |

### Menu classes

| File | What you can do |
|---|---|
| `CustomerMenu.java` | Add, view, search customers, see a customer's parcels |
| `ParcelMenu.java` | Book a parcel, get a price quote, view parcels, cancel (with refund) |
| `TrackingMenu.java` | Track a parcel and move it through each step, or return it to sender |
| `VehicleMenu.java` | Add vehicles, view load, maintenance, parcels on a vehicle |
| `FacilityMenu.java` | Add facilities, see parcels and employees at a facility |
| `EmployeeMenu.java` | Add, view, transfer, remove employees |
| `PaymentMenu.java` | View payments, collect pending money, print invoice |
| `ComplaintMenu.java` | Register, view and resolve complaints |
| `FeedbackMenu.java` | Give feedback after delivery, view it, average rating |
| `ReportMenu.java` | Summary, delayed parcels, customer-wise report, income by service, save to file |

## Price of a parcel

| Service | Rate | Delivery |
|---|---|---|
| Express | Rs 100 per kg | 1 day |
| Standard | Rs 50 per kg | 3 days |
| Economy | Rs 30 per kg | 5 days |

- Minimum charge is for 1 kg.
- Rs 50 extra if the two facilities are in different cities.
- Optional insurance: 2% of the item value.
- 18% GST is added on top.

## A parcel's journey

```
BOOKED -> IN_TRANSIT -> ARRIVED -> OUT_FOR_DELIVERY -> DELIVERED
   |                                       |
   +-> CANCELLED (refund if paid)          +-> RETURNED (delivery failed)
```

1. **Book** (Parcels menu): choose sender, receiver, route, weight and service. A waybill number like `WB1001` is given, along with the expected delivery date.
2. **Dispatch** (Tracking menu): load it on an available vehicle. The program checks the vehicle has enough space left.
3. **Arrived**: the parcel reached the destination facility.
4. **Out for delivery**: give it to a delivery agent who works at that facility.
5. **Delivered**: if the payment is still pending, cash must be collected first. If no one is home, mark it **Returned** instead.

Every status change is saved with the date and time, so "Track a parcel" shows the full history. A parcel still on the way after its expected date shows up as **delayed**.

## Java topics used

- **Classes and objects, constructors, `this`**: all the data classes
- **Encapsulation**: private fields with getters and setters
- **`static`** and **`final`**: the shared lists in `Main`, the price constants in `PriceCalculator`
- **Arrays and `ArrayList`**: login users, status lists, all stored data
- **Loops, `if-else`, `switch`**: all the menus
- **Exception handling**: `readInt` and `readDouble` catch `NumberFormatException`; saving the report catches `IOException`
- **File handling**: `FileWriter` and `PrintWriter` save the parcel report to `parcels_report.txt`
- **Strings and characters**: `equalsIgnoreCase`, `contains`, `repeat`, `Character.isDigit`, `printf` formatting
- **Date and time**: `LocalDate`, `LocalDateTime` and `DateTimeFormatter` for dates and tracking history

See [TEAM_DIVISION.md](TEAM_DIVISION.md) for who explains which file.
