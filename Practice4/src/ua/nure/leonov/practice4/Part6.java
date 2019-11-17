package ua.nure.leonov.practice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {

    public static void main(String[] args) throws FileNotFoundException {
        String input;
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                input = scanner.nextLine();
                if ("stop".equals(input) || "Stop".equals(input)) {
                    break;
                }
                showText(input);
            }
        }
    }

    private static String read(String path) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(path), "CP1251")) {
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }

    public static void showText(String input) throws FileNotFoundException {
        String text = read("part6.txt");

        switch (input) {
            case "Latn":
                showLat(text, "Latn");
                break;
            case "latn":
                showLat(text, "latn");
                break;
            case "Cyrl":
                showCyr(text, "Cyrl");
                break;
            case "cyrl":
                showCyr(text, "cyrl");
                break;
            default:
                System.out.println(input + ": " + "Incorrect input");
        }
    }

    public static void showLat(String text, String input) {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(".*\\p{Alpha}.*");
        Matcher m = pattern.matcher(text);

        while (m.find()) {
            sb.append(m.group(0)).append(" ");
        }
        System.out.println(input + ": " + sb);
    }

    public static void showCyr(String text, String input) {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(".*\\p{InCyrillic}.*");
        Matcher m = pattern.matcher(text);

        while (m.find()) {
            sb.append(m.group(0)).append(" ");
        }

        int i = 1;
        while (i > 0) {
            if ((i = sb.indexOf(".")) > 0) {
                sb.deleteCharAt(i);
            }
        }
        sb = new StringBuilder(sb.toString().replaceAll("-", " "));
        System.out.println(input + ": " + sb);
    }
}

