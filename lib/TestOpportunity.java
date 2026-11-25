

package lib;

import java.util.ArrayList;

public class TestOpportunity {

    public static void main(String[] args) {

        OpportunityDAO dao = new OpportunityDAO();

        // Create a new opportunity
        Opportunity opportunity = new Opportunity(
            0,
            1,  // Replace 1 with an actual user ID
            "Programming",
            "Build a Java Backend",
            "Need help creating a backend using JDBC",
            "Java, JDBC, MySQL",
            50,
            3,
            "OPEN"
        );

        // Insert opportunity
        boolean added = dao.addOpportunity(opportunity);

        if (added) {
            System.out.println("Opportunity added successfully!");
        } else {
            System.out.println("Could not add opportunity.");
        }

        // Fetch all opportunities
        ArrayList<Opportunity> opportunities =
            dao.getAllOpportunities();

        System.out.println("\nAll Opportunities:");

        for (Opportunity op : opportunities) {

            System.out.println("--------------------");
            System.out.println("ID: " + op.getId());
            System.out.println("Title: " + op.getTitle());
            System.out.println("Category: " + op.getCategory());
            System.out.println("Description: " + op.getDescription());
            System.out.println("Credits: " + op.getCreditReward());
        }
    }
}
