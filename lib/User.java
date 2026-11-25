
package lib;

public class User {

    private int id;
    private String name;
    private String email;
    private String rollNo;
    private String passwordHash;
    private int credits;

    public User(int id, String name, String email,
                String rollNo, String passwordHash, int credits) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.rollNo = rollNo;
        this.passwordHash = passwordHash;
        this.credits = credits;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRollNo() {
        return rollNo;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
