package ua.nure.leonov.practice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {

    private static final String ENCODING = "CP1251";

    private static final String REGEXP_FOR_INTEGER = "(^|\\s)(\\d+)(\\s|$)";
    private static final String REGEXP_FOR_DOUBLE = "(^|\\s)([\\d+]*\\.\\d*)(\\s|$)";
    private static final String REGEXP_FOR_CHAR = "(?i)(^|(?<=\\s))[A-zа-я]($|(?=\\s))";
    private static final String REGEXP_FOR_STRING = "[А-Яа-яa-zA-Z]{2,}";

    public static String printValue(String regex) {
        StringBuilder sbIn = new StringBuilder();
        try {
            Scanner sc = new Scanner(new File("part3.txt"), ENCODING);
            while (sc.hasNextLine()) {
                sbIn.append(sc.nextLine()).append(System.lineSeparator());
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("part3.txt" + " not found");
        }

        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(sbIn.toString());
        while (m.find()) {
            sb.append(m.group().trim());
            sb.append(" ");
        }
        if (sb.length() == 0) {
            return "No such values";
        }
        return sb.toString().substring(0, sb.length() - 1).trim();
    }

    public static void input() {
        Scanner sc = new Scanner(System.in, ENCODING);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            switch (str) {
                case "int":
                    System.out.println(printValue(REGEXP_FOR_INTEGER));
                    break;
                case "double":
                    System.out.println(printValue(REGEXP_FOR_DOUBLE));
                    break;
                case "char":
                    System.out.println(printValue(REGEXP_FOR_CHAR));
                    break;
                case "String":
                    System.out.println(printValue(REGEXP_FOR_STRING));
                    break;
                case "stop":
                    return;
                default:
                    System.out.println("Incorrect input");
            }
        }
    }

    public static void main(String[] args) {
        Part3.input();
    }
}
