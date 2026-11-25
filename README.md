# APTOS-Java-backend
# APTOS – Student Skill Exchange Platform

APTOS is a Java-based student skill exchange and collaboration platform. It allows students to register, explore opportunities, create opportunities, apply for projects, and manage credits.

The project is built using Java, JDBC, and MySQL, with Java multithreading concepts demonstrated through separate examples.

## Features

- **Student Registration:** Register new student accounts.
- **Student Login:** Authenticate students using email and password.
- **View Students:** Display all registered students.
- **Opportunity Management:** Create and view student opportunities.
- **Apply for Opportunities:** Students can apply for available opportunities.
- **Application Management:** View applications and update their status.
- **Credit Management:** Add and deduct student credits.
- **Multithreading:** Demonstrations of Thread, Runnable, and Synchronization.

## Technologies Used

| Technology | Purpose |
|---|---|
| Java | Backend development |
| JDBC | Database connectivity |
| MySQL | Database management |
| SQL | Database operations |
| Java Threads | Multithreading concepts |
| VS Code | Development environment |

## Project Structure

```text
APTOSsimple/
│
├── lib/
│   ├── Main.java
│   ├── DBConnection.java
│   ├── User.java
│   ├── UserDAO.java
│   ├── Opportunity.java
│   ├── OpportunityDAO.java
│   ├── Application.java
│   ├── ApplicationDAO.java
│   ├── CreditDAO.java
│   ├── TestThread.java
│   ├── TestRunnable.java
│   ├── TestSynchronization.java
│   ├── gson-2.14.0.jar
│   └── mysql-connector-j-26.10.0.jar
│
└── .gitignore
```

## Database Setup

1. Install MySQL.
2. Create a database named `aptos`.
3. Create the required tables:
   - `users`
   - `opportunities`
   - `applications`
   - `credit_transactions`
4. Configure your MySQL username and password in `DBConnection.java`.

## How to Run

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/APTOSsimple.git
```

### 2. Open the project folder

```bash
cd APTOSsimple
```

### 3. Compile the Java files

```bash
javac -cp ".;lib/*" -d . lib\*.java
```

### 4. Run the application

```bash
java -cp ".;lib/*" lib.Main
```

## Application Menu

```text
================================
          APTOS SYSTEM
================================

1. View All Students
2. Register Student
3. Login Student
4. View All Opportunities
5. Add Opportunity
6. Apply for Opportunity
7. View Applications
8. Manage Credits
9. Exit

Enter your choice:
```

## Multithreading Demonstrations

The project also includes separate Java programs demonstrating:

- Creating threads using the Thread class.
- Creating threads using the Runnable interface.
- Synchronization to prevent race conditions when accessing shared data.

## Future Improvements

- Integrate a frontend using React.
- Add role-based access control.
- Implement automatic credit transfers when applications are accepted.
- Improve application approval and opportunity management.
- Integrate multithreading into backend operations.

## Author

Developed as a Java backend academic project.

**Project:** APTOS – Student Skill Exchange Platform

**Technologies:** Java | JDBC | MySQL | SQL | Multithreading
