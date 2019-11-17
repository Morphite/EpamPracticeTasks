package ua.nure.leonov.practice5;

import java.util.function.Function;

public class Part1 {

    private static final String STR = "is interrupted";

    public static void main(String[] args) {
        System.out.println("mThread started...");
        Thread firstThread = new Part1First();
        firstThread.start();
        try {
            firstThread.join();
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()
                    + STR + "  Part1.main()");
        }
        Thread secondTh = new Thread(new Part1Second());
        secondTh.start();
        try {
            secondTh.join();
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName()
                    + STR + " is interrupted in Part1.main()");
        }

        Function<String, String> function = Part1::hello;
        function.apply("Third thread");
//        System.out.println(function.apply("Third thread"));

        System.out.println("mThread finished...");
    }

    public static String hello(String thread) {
        for (int i = 0; i < 3; i++) {
            System.out.println(Thread.currentThread().getName()
                    + thread);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName()
                        + " " + STR);
            }
        }
        return thread;
    }
}
