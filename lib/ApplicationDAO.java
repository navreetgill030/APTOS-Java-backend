
package lib;

import java.sql.*;
import java.util.ArrayList;

public class ApplicationDAO {

    // Apply for opportunity
    public boolean applyForOpportunity(int opportunityId, int applicantId) {

        String sql = "INSERT INTO applications "
                + "(opportunity_id, applicant_id, status) "
                + "VALUES (?, ?, 'PENDING')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, opportunityId);
            ps.setInt(2, applicantId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Application failed: " + e.getMessage());
            return false;
        }
    }

    // View applications for an opportunity
    public ArrayList<Application> getApplications(int opportunityId) {

        ArrayList<Application> applications = new ArrayList<>();

        String sql = "SELECT * FROM applications WHERE opportunity_id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, opportunityId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Application app = new Application(
                            rs.getInt("id"),
                            rs.getInt("opportunity_id"),
                            rs.getInt("applicant_id"),
                            rs.getString("status")
                    );

                    applications.add(app);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error fetching applications: " + e.getMessage());
        }

        return applications;
    }

    // Accept or reject an application
    public boolean updateApplicationStatus(int applicationId, String status) {

        if (!status.equals("ACCEPTED") && !status.equals("REJECTED")) {
            return false;
        }

        String sql = "UPDATE applications SET status = ? WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, status);
            ps.setInt(2, applicationId);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error updating application: " + e.getMessage());
            return false;
        }
    }
}
