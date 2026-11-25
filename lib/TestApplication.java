
package lib;

import java.util.ArrayList;
import java.util.Scanner;

public class TestApplication {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ApplicationDAO dao = new ApplicationDAO();

        System.out.println("===== APTOS APPLICATION SYSTEM =====");

        System.out.println("1. Apply for Opportunity");
        System.out.println("2. View Applications");
        System.out.println("3. Update Application Status");

        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        switch (choice) {

            case 1:
                System.out.print("Enter Opportunity ID: ");
                int opportunityId = sc.nextInt();

                System.out.print("Enter Applicant User ID: ");
                int applicantId = sc.nextInt();

                boolean applied = dao.applyForOpportunity(
                    opportunityId, applicantId
                );

                if (applied) {
                    System.out.println("Application submitted!");
                } else {
                    System.out.println("Application failed!");
                }
                break;

            case 2:
                System.out.print("Enter Opportunity ID: ");
                int opId = sc.nextInt();

                ArrayList<Application> applications =
                    dao.getApplications(opId);

                for (Application app : applications) {

                    System.out.println("------------------");
                    System.out.println("Application ID: " + app.getId());
                    System.out.println("Applicant ID: " + app.getApplicantId());
                    System.out.println("Status: " + app.getStatus());
                }
                break;

            case 3:
                System.out.print("Enter Application ID: ");
                int applicationId = sc.nextInt();

                System.out.println("1. ACCEPTED");
                System.out.println("2. REJECTED");
                System.out.print("Enter choice: ");

                int statusChoice = sc.nextInt();

                String status;

                if (statusChoice == 1) {
                    status = "ACCEPTED";
                } else if (statusChoice == 2) {
                    status = "REJECTED";
                } else {
                    System.out.println("Invalid choice!");
                    break;
                }

                boolean updated = dao.updateApplicationStatus(
                    applicationId, status
                );

                if (updated) {
                    System.out.println("Application updated!");
                } else {
                    System.out.println("Update failed!");
                }
                break;

            default:
                System.out.println("Invalid choice!");
        }

        sc.close();
    }
}
