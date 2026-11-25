
package lib;

import java.sql.*;

public class CreditDAO {

    // Add credits
    public boolean addCredits(int userId, int amount, String description) {

        if (amount <= 0) {
            return false;
        }

        String updateSQL =
                "UPDATE users SET credits = credits + ? WHERE id = ?";

        String insertSQL =
                "INSERT INTO credit_transactions "
                + "(user_id, amount, transaction_type, description) "
                + "VALUES (?, ?, 'EARNED', ?)";

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            try (PreparedStatement update = con.prepareStatement(updateSQL);
                 PreparedStatement insert = con.prepareStatement(insertSQL)) {

                update.setInt(1, amount);
                update.setInt(2, userId);

                if (update.executeUpdate() == 0) {
                    con.rollback();
                    return false;
                }

                insert.setInt(1, userId);
                insert.setInt(2, amount);
                insert.setString(3, description);
                insert.executeUpdate();

                con.commit();
                return true;

            } catch (SQLException e) {
                con.rollback();
                throw e;
            }

        } catch (SQLException e) {
            System.out.println("Credit error: " + e.getMessage());
            return false;
        }
    }

    // Deduct credits
    public boolean deductCredits(int userId, int amount, String description) {

        if (amount <= 0) {
            return false;
        }

        String updateSQL =
                "UPDATE users SET credits = credits - ? "
                + "WHERE id = ? AND credits >= ?";

        String insertSQL =
                "INSERT INTO credit_transactions "
                + "(user_id, amount, transaction_type, description) "
                + "VALUES (?, ?, 'SPENT', ?)";

        try (Connection con = DBConnection.getConnection()) {

            con.setAutoCommit(false);

            try (PreparedStatement update = con.prepareStatement(updateSQL);
                 PreparedStatement insert = con.prepareStatement(insertSQL)) {

                update.setInt(1, amount);
                update.setInt(2, userId);
                update.setInt(3, amount);

                if (update.executeUpdate() == 0) {
                    con.rollback();
                    System.out.println("Insufficient credits or invalid user.");
                    return false;
                }

                insert.setInt(1, userId);
                insert.setInt(2, amount);
                insert.setString(3, description);
                insert.executeUpdate();

                con.commit();
                return true;

            } catch (SQLException e) {
                con.rollback();
                throw e;
            }

        } catch (SQLException e) {
            System.out.println("Credit error: " + e.getMessage());
            return false;
        }
    }
}
