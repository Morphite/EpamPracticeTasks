package ua.nure.leonov.practice5;

public class Part1Second implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName()
                    + " Third_Thread");
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()
                        + " is interrupted");
            }
        }
    }
}
