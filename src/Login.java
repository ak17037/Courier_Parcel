public class Login {

    // shortcut: passwords are written in the code, fine for a class project, a real app should keep them hashed in a file or database
    static String[] usernames = {"admin", "staff"};
    static String[] passwords = {"admin123", "staff123"};

    static String currentUser;

    // gives 3 chances to enter the right username and password
    static boolean login() {
        System.out.println("Please login to continue.");
        for (int attempt = 1; attempt <= 3; attempt++) {
            String user = Main.readText("Username: ");
            String pass = Main.readText("Password: ");

            for (int i = 0; i < usernames.length; i++) {
                if (usernames[i].equals(user) && passwords[i].equals(pass)) {
                    currentUser = user;
                    System.out.println("Login successful. Welcome, " + user + "!");
                    return true;
                }
            }
            System.out.println("Wrong username or password. Tries left: " + (3 - attempt));
        }
        return false;
    }
}
