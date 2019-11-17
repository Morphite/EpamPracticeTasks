package ua.nure.leonov.practice1;

public class Part5 {
    public static void main(String[] args) {
        int sum = 0;
        double aimNumber = Integer.parseInt(args[0]);

        while (aimNumber > 0) {
            sum += aimNumber % 10;
            aimNumber /= 10;
        }
        System.out.println(sum);
    }
}