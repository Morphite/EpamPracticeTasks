package ua.nure.leonov.practice5;

public class Part1First extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName()
                    + " Second_Thread");
            try {
                sleep(300);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()
                        + " is interrupted");
            }
        }
    }
}
