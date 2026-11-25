

package lib;

class StudentTask extends Thread {

    private String studentName;

    public StudentTask(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public void run() {

        System.out.println(studentName + " is accessing APTOS");

        for (int i = 1; i <= 3; i++) {

            System.out.println(studentName + " - Task " + i);

        }

        System.out.println(studentName + " finished!");
    }
}

public class TestThread {

    public static void main(String[] args) {

        StudentTask student1 = new StudentTask("Student A");
        StudentTask student2 = new StudentTask("Student B");
        StudentTask student3 = new StudentTask("Student C");

        student1.start();
        student2.start();
        student3.start();
    }
}
