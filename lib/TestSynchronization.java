

package lib;

class CreditAccount {

    private int credits = 100;

    public synchronized void addCredits(int amount) {

        credits = credits + amount;

        System.out.println(
            Thread.currentThread().getName()
            + " added credits. Balance: " + credits
        );
    }

    public synchronized void deductCredits(int amount) {

        if (credits >= amount) {

            credits = credits - amount;

            System.out.println(
                Thread.currentThread().getName()
                + " deducted credits. Balance: " + credits
            );

        } else {
            System.out.println("Insufficient credits!");
        }
    }

    public int getCredits() {
        return credits;
    }
}

public class TestSynchronization {

    public static void main(String[] args) {

        CreditAccount account = new CreditAccount();

        Thread t1 = new Thread(
            () -> account.addCredits(50), "Student A"
        );

        Thread t2 = new Thread(
            () -> account.deductCredits(30), "Student B"
        );

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("Final Credits: " + account.getCredits());
    }
}
