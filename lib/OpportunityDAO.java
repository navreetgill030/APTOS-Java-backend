
package lib;

import java.sql.*;
import java.util.ArrayList;

public class OpportunityDAO {

    // Add opportunity
    public boolean addOpportunity(Opportunity opportunity) {

        String sql = "INSERT INTO opportunities "
                + "(owner_id, category, title, description, "
                + "required_skills, credit_reward, max_members, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, 'OPEN')";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, opportunity.getOwnerId());
            ps.setString(2, opportunity.getCategory());
            ps.setString(3, opportunity.getTitle());
            ps.setString(4, opportunity.getDescription());
            ps.setString(5, opportunity.getRequiredSkills());
            ps.setInt(6, opportunity.getCreditReward());
            ps.setInt(7, opportunity.getMaxMembers());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error adding opportunity: " + e.getMessage());
            return false;
        }
    }

    // View all open opportunities
    public ArrayList<Opportunity> getAllOpportunities() {

        ArrayList<Opportunity> opportunities = new ArrayList<>();

        String sql = "SELECT * FROM opportunities "
                + "WHERE status = 'OPEN' ORDER BY created_at DESC";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Opportunity op = new Opportunity(
                        rs.getInt("id"),
                        rs.getInt("owner_id"),
                        rs.getString("category"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("required_skills"),
                        rs.getInt("credit_reward"),
                        rs.getInt("max_members"),
                        rs.getString("status")
                );

                opportunities.add(op);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching opportunities: " + e.getMessage());
        }

        return opportunities;
    }
}
