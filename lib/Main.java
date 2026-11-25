
package lib;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static UserDAO userDAO = new UserDAO();
    static OpportunityDAO opportunityDAO = new OpportunityDAO();
    static ApplicationDAO applicationDAO = new ApplicationDAO();
    static CreditDAO creditDAO = new CreditDAO();

    static User currentUser = null;

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n================================");
            System.out.println("          APTOS SYSTEM");
            System.out.println("================================");

            System.out.println("1. View All Students");
            System.out.println("2. Register Student");
            System.out.println("3. Login Student");
            System.out.println("4. View All Opportunities");
            System.out.println("5. Add Opportunity");
            System.out.println("6. Apply for Opportunity");
            System.out.println("7. View Applications");
            System.out.println("8. Manage Credits");
            System.out.println("9. Exit");

            System.out.print("\nEnter your choice: ");

            int choice = readInt();

            switch (choice) {

                case 1:
                    viewAllStudents();
                    break;

                case 2:
                    registerStudent();
                    break;

                case 3:
                    loginStudent();
                    break;

                case 4:
                    viewAllOpportunities();
                    break;

                case 5:
                    addOpportunity();
                    break;

                case 6:
                    applyForOpportunity();
                    break;

                case 7:
                    viewApplications();
                    break;

                case 8:
                    manageCredits();
                    break;

                case 9:
                    System.out.println("Thank you for using APTOS!");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Read integer safely
    public static int readInt() {
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            sc.next();
        }

        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    // 1. View all students
    public static void viewAllStudents() {

        ArrayList<User> users = userDAO.getAllUsers();

        System.out.println("\n===== REGISTERED STUDENTS =====");

        if (users.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (User user : users) {

            System.out.println("--------------------------");
            System.out.println("ID: " + user.getId());
            System.out.println("Name: " + user.getName());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Roll No: " + user.getRollNo());
            System.out.println("Credits: " + user.getCredits());
        }
    }

    // 2. Register student
    public static void registerStudent() {

        System.out.println("\n===== STUDENT REGISTRATION =====");

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter roll number: ");
        String rollNo = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        boolean result = userDAO.registerUser(
                name, email, rollNo, password
        );

        if (result) {
            System.out.println("Student registered successfully!");
        } else {
            System.out.println("Registration failed!");
        }
    }

    // 3. Login student
    public static void loginStudent() {

        if (currentUser != null) {
            System.out.println("Already logged in as "
                    + currentUser.getName());
            return;
        }

        System.out.println("\n===== STUDENT LOGIN =====");

        System.out.print("Enter email: ");
        String email = sc.nextLine();

        System.out.print("Enter password: ");
        String password = sc.nextLine();

        User user = userDAO.loginUser(email, password);

        if (user != null) {
            currentUser = user;

            System.out.println("Login successful!");
            System.out.println("Welcome, " + user.getName());
            System.out.println("Your credits: " + user.getCredits());

        } else {
            System.out.println("Invalid email or password!");
        }
    }

    // 4. View all opportunities
    public static void viewAllOpportunities() {

        ArrayList<Opportunity> opportunities =
                opportunityDAO.getAllOpportunities();

        System.out.println("\n===== AVAILABLE OPPORTUNITIES =====");

        if (opportunities.isEmpty()) {
            System.out.println("No opportunities available.");
            return;
        }

        for (Opportunity op : opportunities) {

            System.out.println("--------------------------");
            System.out.println("ID: " + op.getId());
            System.out.println("Posted By User ID: " + op.getOwnerId());
            System.out.println("Title: " + op.getTitle());
            System.out.println("Category: " + op.getCategory());
            System.out.println("Description: " + op.getDescription());
            System.out.println("Skills: " + op.getRequiredSkills());
            System.out.println("Credit Reward: " + op.getCreditReward());
            System.out.println("Maximum Members: " + op.getMaxMembers());
            System.out.println("Status: " + op.getStatus());
        }
    }

    // 5. Add opportunity
    public static void addOpportunity() {

        if (currentUser == null) {
            System.out.println("Please login first!");
            return;
        }

        System.out.println("\n===== ADD OPPORTUNITY =====");

        System.out.print("Enter category: ");
        String category = sc.nextLine();

        System.out.print("Enter title: ");
        String title = sc.nextLine();

        System.out.print("Enter description: ");
        String description = sc.nextLine();

        System.out.print("Enter required skills: ");
        String skills = sc.nextLine();

        System.out.print("Enter credit reward: ");
        int credits = readInt();

        System.out.print("Enter maximum members: ");
        int members = readInt();

        if (credits <= 0 || members <= 0) {
            System.out.println("Credits and members must be positive.");
            return;
        }

        Opportunity opportunity = new Opportunity(
                0,
                currentUser.getId(),
                category,
                title,
                description,
                skills,
                credits,
                members,
                "OPEN"
        );

        boolean result = opportunityDAO.addOpportunity(opportunity);

        if (result) {
            System.out.println("Opportunity added successfully!");
        } else {
            System.out.println("Could not add opportunity.");
        }
    }

    // 6. Apply for opportunity
    public static void applyForOpportunity() {

        if (currentUser == null) {
            System.out.println("Please login first!");
            return;
        }

        System.out.println("\n===== APPLY FOR OPPORTUNITY =====");

        System.out.print("Enter opportunity ID: ");
        int opportunityId = readInt();

        boolean result = applicationDAO.applyForOpportunity(
                opportunityId,
                currentUser.getId()
        );

        if (result) {
            System.out.println("Application submitted successfully!");
        } else {
            System.out.println("Application failed. Check the opportunity ID "
                    + "or whether you have already applied.");
        }
    }

    // 7. View and update applications
    public static void viewApplications() {

        if (currentUser == null) {
            System.out.println("Please login first!");
            return;
        }

        System.out.print("Enter opportunity ID: ");
        int opportunityId = readInt();

        ArrayList<Application> applications =
                applicationDAO.getApplications(opportunityId);

        if (applications.isEmpty()) {
            System.out.println("No applications found.");
            return;
        }

        System.out.println("\n===== APPLICATIONS =====");

        for (Application app : applications) {

            System.out.println("--------------------------");
            System.out.println("Application ID: " + app.getId());
            System.out.println("Applicant ID: " + app.getApplicantId());
            System.out.println("Status: " + app.getStatus());
        }

        System.out.print("\nEnter application ID to update (0 to skip): ");
        int applicationId = readInt();

        if (applicationId == 0) {
            return;
        }

        System.out.println("1. Accept");
        System.out.println("2. Reject");

        System.out.print("Enter choice: ");
        int choice = readInt();

        String status;

        if (choice == 1) {
            status = "ACCEPTED";
        } else if (choice == 2) {
            status = "REJECTED";
        } else {
            System.out.println("Invalid choice!");
            return;
        }

        boolean result = applicationDAO.updateApplicationStatus(
                applicationId, status
        );

        System.out.println(result
                ? "Application status updated!"
                : "Could not update application.");
    }

    // 8. Manage credits
    public static void manageCredits() {

        if (currentUser == null) {
            System.out.println("Please login first!");
            return;
        }

        System.out.println("\n===== CREDIT MANAGEMENT =====");
        System.out.println("Current balance: " + currentUser.getCredits());

        System.out.println("1. Add credits");
        System.out.println("2. Deduct credits");

        System.out.print("Enter choice: ");
        int choice = readInt();

        System.out.print("Enter amount: ");
        int amount = readInt();

        System.out.print("Enter description: ");
        String description = sc.nextLine();

        boolean result = false;

        if (choice == 1) {
            result = creditDAO.addCredits(
                    currentUser.getId(), amount, description
            );
        } else if (choice == 2) {
            result = creditDAO.deductCredits(
                    currentUser.getId(), amount, description
            );
        } else {
            System.out.println("Invalid choice!");
            return;
        }

        if (result) {
            currentUser.setCredits(
                    currentUser.getCredits()
                    + (choice == 1 ? amount : -amount)
            );

            System.out.println("Transaction successful!");
            System.out.println("Updated balance: "
                    + currentUser.getCredits());
        } else {
            System.out.println("Transaction failed!");
        }
    }
}
