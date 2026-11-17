// small checks used before saving what the user typed
public class Validator {

    // phone must be exactly 10 digits
    static boolean isValidPhone(String phone) {
        if (phone.length() != 10) return false;
        for (int i = 0; i < phone.length(); i++) {
            if (!Character.isDigit(phone.charAt(i))) return false;
        }
        return true;
    }

    // name must not be empty and can only have letters and spaces
    static boolean isValidName(String name) {
        if (name.isEmpty()) return false;
        for (int i = 0; i < name.length(); i++) {
            char ch = name.charAt(i);
            if (!Character.isLetter(ch) && ch != ' ') return false;
        }
        return true;
    }

    // vehicle number like PB10AB1234: 8 to 10 letters and digits
    static boolean isValidVehicleNumber(String number) {
        if (number.length() < 8 || number.length() > 10) return false;
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isLetterOrDigit(number.charAt(i))) return false;
        }
        return true;
    }
}
