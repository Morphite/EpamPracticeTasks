package ua.nure.leonov.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {


    private static final String REG2 = "(?U)\\w+\\b*";

    public static void main(String[] args) {
        String input = Util.readFile("part2.txt");
        System.out.println(convert(input));
    }

    public static String convert(String input) {

        Pattern pattern = Pattern.compile(REG2);
        StringBuilder sbMin = new StringBuilder("Min: ");
        StringBuilder sbMax = new StringBuilder("Max: ");
        int min = minLen(input);
        int max = maxLen(input);
        Matcher m = pattern.matcher(input);
        String liter;
        boolean fmin = false;
        boolean fmax = false;

        while (m.find()) {
            liter = input.substring(m.start(), m.end());
            if (liter.length() == min) {

                addText(sbMin, liter, fmin, isAbsent(sbMin.toString(), liter));
                if (!fmin) {
                    fmin = true;
                }
            }
            if (liter.length() == max) {
                addText(sbMax, liter, fmax, isAbsent(sbMax.toString(), liter));
                if (!fmax) {
                    fmax = true;
                }
            }
        }
        return sbMin.toString() + System.lineSeparator() + sbMax.toString() + System.lineSeparator();
    }

    private static void addText(StringBuilder sb, String text, boolean f, boolean isPr) {

        if (isPr) {
            if (f) {
                sb.append(", ").append(text);

            } else {
                sb.append(text);

            }
        }
    }

    private static int minLen(String input) {

        Pattern pattern = Pattern.compile(REG2);
        Matcher m = pattern.matcher(input);
        int min = Integer.MAX_VALUE;
        while (m.find()) {
            String liter = input.substring(m.start(), m.end());
            if (liter.length() < min) {
                min = liter.length();
            }
        }
        return min;
    }

    private static int maxLen(String input) {

        Pattern pattern = Pattern.compile(REG2);
        Matcher m = pattern.matcher(input);
        int max = 0;
        while (m.find()) {
            String liter = input.substring(m.start(), m.end());
            if (liter.length() > max) {
                max = liter.length();
            }
        }
        return max;
    }

    private static boolean isAbsent(String s, String word) {
        String regex = "\\s*" + word + "\\b*";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(s);
        return !m.find();
    }
}
