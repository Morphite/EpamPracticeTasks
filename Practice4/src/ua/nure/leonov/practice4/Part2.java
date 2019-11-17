package ua.nure.leonov.practice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Part2 {

    private static final String FILE = "part2.txt";
    private static final String ENCODE = "CP1251";
    private static final int RNDNUM = 50;

    public static void main(String[] args) {
        try {
            writeRandom();
            sortAndWrite();
            show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeRandom() throws IOException {
        ArrayList<Integer> arr = new ArrayList<>(10);
        Random r = new SecureRandom();

        for (int i = 0; i < 10; i++) {
            arr.add(r.nextInt(RNDNUM));
        }

        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num).append(" ");
        }

        try (FileWriter out = new FileWriter(FILE)) {
            out.write(sb.toString());
        }
    }

    private static void sortAndWrite() throws IOException {
        ArrayList<Integer> arr = new ArrayList<>();
        try (Scanner in = new Scanner(new File(FILE), ENCODE)) {
                while (in.hasNext()) {
                    if (in.hasNextInt()) {
                        arr.add(in.nextInt());
                    } else {
                        in.next();
                    }
                }
        }
        Integer[] tempArr = arr.toArray(new Integer[0]);
        Arrays.sort(tempArr);
        arr = new ArrayList<>(Arrays.asList(tempArr));

        try (FileWriter out = new FileWriter("part2_sorted.txt")) {
            for (int elem : arr) {
                out.write(elem + " ");
            }
        }
    }

    private static void show() throws FileNotFoundException {
        try (Scanner in = new Scanner(new File(FILE), ENCODE)) {
            System.out.print("input ==> ");
            while (in.hasNext()) {
                if (in.hasNextInt()) {
                    System.out.print(in.nextInt() + " ");
                } else {
                    in.next();
                }
            }
            System.out.println();
        }
        try (Scanner out = new Scanner(new File("part2_sorted.txt"), ENCODE)) {
            System.out.print("output ==> ");
            while (out.hasNext()) {
                if (out.hasNextInt()) {
                    System.out.print(out.nextInt() + " ");
                } else {
                    out.next();
                }
            }
        }
    }
}
