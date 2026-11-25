# Team Division

**Course:** 25CSE0202 – Programming in Java
**Institution:** Chitkara University
**Lead Member:** Vishavjeet Singh Chauhan (Roll No: 2510992871 | Section: G8)

## Files everyone must know

| File | Why |
|---|---|
| `Main.java` | How the program starts, login, the main menu `switch`, the lists that hold all data, the `find...` and `read...` helper methods |
| `Parcel.java` | The main class of the project: its fields, constructor, how `setStatus` saves the tracking history, `isDelayed` and `getPaymentStatus` |

## Member 1: Vishavjeet Singh Chauhan (Roll No: 2510992871)

**Part:** People and places

- `Customer.java`, `CustomerMenu.java`: add (with checks), view, search by name, a customer's parcels
- `Facility.java`, `FacilityMenu.java`: add facility, parcels and employees at a facility
- `Employee.java`, `EmployeeMenu.java`: add, view, transfer, remove employees
- `Login.java`: username and password check with 3 tries
- `Validator.java`: phone, name and vehicle number checks with `Character` methods

**Topics:** classes and objects, constructors, encapsulation, arrays, `ArrayList`, string and character methods

## Member 2

**Part:** Moving the parcel and after-delivery service

- `Vehicle.java`, `VehicleMenu.java`: add vehicles, current load, maintenance, parcels on a vehicle
- `TrackingMenu.java`: track, dispatch (capacity check), arrived, out for delivery (agent from the same facility), delivered, returned
- `Complaint.java`, `ComplaintMenu.java`: register, view, resolve complaints
- `Feedback.java`, `FeedbackMenu.java`: star rating after delivery, average rating

**Topics:** `if-else` checks, loops over an `ArrayList`, status flow, `LocalDateTime`, `boolean` flags

## Member 3

**Part:** Money and reports

- `ParcelMenu.java`: booking, price quote, cancel with refund, waybill number
- `PriceCalculator.java`: rate per kg, 1 kg minimum, other city charge, insurance, 18% GST, delivery days
- `PaymentMenu.java`, `Invoice.java`: view payments, collect pending money, print invoice
- `ReportMenu.java`: summary, delayed parcels, customer-wise report, income by service, save to a file

**Topics:** `static final` constants, arithmetic and `printf` formatting, `LocalDate`, counting and totals with loops, file handling with `FileWriter`, `IOException`

## The parcel journey (any member can be asked)

1. Book the parcel: price = weight × rate + other city charge + insurance, then 18% GST. It gets a waybill like `WB1001`, status `BOOKED`, and an expected delivery date.
2. Dispatch it on a vehicle that has space: status becomes `IN_TRANSIT`.
3. It reaches the destination: status becomes `ARRIVED`.
4. Give it to a delivery agent of that facility: status becomes `OUT_FOR_DELIVERY`.
5. Hand it over (cash is collected first if the payment is pending): status becomes `DELIVERED`. If delivery fails, it becomes `RETURNED`.
6. After delivery the customer can give feedback. A complaint can be raised at any time.
