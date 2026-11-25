

package lib;

class StudentWork implements Runnable {

    private String studentName;

    public StudentWork(String studentName) {
        this.studentName = studentName;
    }

    @Override
    public void run() {

        System.out.println(studentName + " is working on APTOS");

        for (int i = 1; i <= 3; i++) {
            System.out.println(studentName + " - Task " + i);
        }

        System.out.println(studentName + " completed the work!");
    }
}

public class TestRunnable {

    public static void main(String[] args) {

        StudentWork work1 = new StudentWork("Student A");
        StudentWork work2 = new StudentWork("Student B");

        Thread t1 = new Thread(work1);
        Thread t2 = new Thread(work2);

        t1.start();
        t2.start();
    }
}
