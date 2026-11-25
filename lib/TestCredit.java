

package lib;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TestCredit {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CreditDAO dao = new CreditDAO();

        System.out.print("Enter User ID: ");
        int userId = sc.nextInt();

        System.out.print("Enter amount: ");
        int amount = sc.nextInt();
        sc.nextLine();

        System.out.println("1. Add Credits");
        System.out.println("2. Deduct Credits");

        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter description: ");
        String description = sc.nextLine();

        boolean result = false;

        if (choice == 1) {
            result = dao.addCredits(userId, amount, description);
        } else if (choice == 2) {
            result = dao.deductCredits(userId, amount, description);
        } else {
            System.out.println("Invalid choice!");
        }

        if (result) {
            System.out.println("Credit transaction successful!");
        } else {
            System.out.println("Credit transaction failed!");
        }

        // Display updated balance
        String sql = "SELECT credits FROM users WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Current Balance: "
                            + rs.getInt("credits"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        sc.close();
    }
}
