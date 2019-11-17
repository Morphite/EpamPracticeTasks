package ua.nure.leonov.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {
    public static void main(String[] args) {
        String input = Util.readFile("part6.txt");
        convert(input);
    }

    public static String convert(String input) {
        StringBuilder sb = new StringBuilder();
        String regex = "(?U)(\\w+\\b)(\\s*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(input);
        while (m.find()) {
            String liter = m.group(1);
            if (isMany(input, liter)) {
                sb.append("_" + liter + m.group(2));
            } else {
                sb.append(liter + m.group(2));
            }
        }

        return sb.toString();
    }

    public static boolean isMany(String s, String word) {
        String regex = "\\s" + word + "\\b*|\\A" + word + "\\b*";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(s);
        int count = 0;
        while (m.find()) {
            count++;
        }
        return count > 1;
    }
}
