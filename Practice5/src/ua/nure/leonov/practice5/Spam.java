package ua.nure.leonov.practice5;

import java.util.NoSuchElementException;
import java.util.Scanner;

public final class Spam extends Thread {

    private int[] time = new int[]{1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000, 1000,};
    private String[] messages = new String[]{"message - 1", "message - 2", "message - 3", "message - 4",
            "message - 5", "message - 6", "message - 7", "message - 8", "message - 9", "message - 0"};

    @Override
    public void run() {
        for (int i = 0; i < time.length; i++) {
            try {
                Thread.sleep(time[i]);
            } catch (InterruptedException e) {
                return;
            }
            System.out.println(messages[i]);
        }
    }

    public static void main(String[] args) {
        final Spam s = new Spam();
        s.start();
        new Thread(() -> {
            while (true) {
                Scanner in = new Scanner(System.in);
                try {
                    sleep(100);
                    in.nextLine();
                    break;
                } catch (NoSuchElementException | InterruptedException e) {
                    continue;
                }
            }
            System.out.println("stopped");
            s.interrupt();
        }).start();
        try {
            s.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
