package ua.nure.leonov.practice5;

public class Demo {

    private static final String EXC = Thread.currentThread().getName()
            + " is interrupted in Demo.main()";

    public static void main(String[] args) throws Exception {
        System.out.println("mThread started...");

        System.out.println("~~~~~~~~~~~~Part1");
        Part1.main(args);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(EXC);
        }

        System.out.println("~~~~~~~~~~~~Part2");
        Part2.main(args);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(EXC);
        }


        System.out.println("~~~~~~~~~~~~Part3");
        Part3.main(args);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println(EXC);
        }

        System.out.println("~~~~~~~~~~~~Part4");
        Part4.main(args);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(EXC);
        }

        System.out.println("~~~~~~~~~~~~Part5");
        Part5.main(args);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(EXC);
        }

        System.out.println("~~~~~~~~~~~~Part6");
        Part6.main(args);
        System.out.println("mThread finished...");
    }
}
