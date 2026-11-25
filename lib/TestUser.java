

package lib;

public class TestUser {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAO();

        // Register a student
        boolean registered = userDAO.registerUser(
            "Test Student",
            "teststudent@gmail.com",
            "TEST001",
            "test123"
        );

        if (registered) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed!");
        }

        // Login the student
        User user = userDAO.loginUser(
            "teststudent@gmail.com",
            "test123"
        );

        if (user != null) {
            System.out.println("Login successful!");
            System.out.println("Welcome, " + user.getName());
            System.out.println("Credits: " + user.getCredits());
        } else {
            System.out.println("Login failed!");
        }
    }
}
