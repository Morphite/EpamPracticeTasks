package ua.nure.leonov.practice5;

public class Part6 {
    private static final Object M = new Object();

    public static void main(String[] args) {
        int a = 0;
        Thread t = new Thread(() -> {
            synchronized (M){
                try {
                    M.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        synchronized (M){
            while (!"BLOCKED".equals(t.getState().toString()) && a >= 0) {
                a++;
            }
            System.out.println(t.getState());
        }

        while (!"WAITING".equals(t.getState().toString()) && a >= 0){
            a++;
        }
        System.out.println(t.getState());

        synchronized (M){
            M.notifyAll();
        }

        while (!"TERMINATED".equals(t.getState().toString()) && a >= 0){
            a++;
        }
        System.out.print(t.getState());

    }
}
