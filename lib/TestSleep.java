

package lib;

public class TestSleep {

    public static void main(String[] args) {

        for (int i = 1; i <= 5; i++) {

            System.out.println("APTOS Task: " + i);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted!");
                Thread.currentThread().interrupt();
                break;
            }
        }

        System.out.println("Task completed!");
    }
}
