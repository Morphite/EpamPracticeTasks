package ua.nure.leonov.practice5;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Part2 {
    public static void main(String[] args) throws InterruptedException {
        ByteArrayInputStream byteIn = new ByteArrayInputStream(System.lineSeparator().getBytes());
        long count = byteIn.skip(System.lineSeparator().length());
        if(count > 1){
            System.out.println(" ");
        }
        InputStream stdIn = System.in;
        System.setIn(byteIn);

        Spam.main(args);
        Thread.sleep(4000);

        System.out.println("Try to send enter to standard input");
        byteIn.reset();
        System.setIn(stdIn);

        Thread.sleep(1000);
    }
}
