package ua.nure.leonov.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Part3 {

    private Part3() {
        throw new IllegalStateException("Utility class");
    }

    public static String convert(String input){
        String string = input;

        StringBuilder sb = new StringBuilder(string);
        String regex = "(?U)(\\w{3,}\\b\\s*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(string);
        while (m.find()) {
            int stChar = m.start();
            if (Character.isLowerCase(sb.charAt(stChar))) {
                sb.setCharAt(stChar, Character.toUpperCase(sb.charAt(stChar)));
            } else {
                sb.setCharAt(stChar, Character.toLowerCase(sb.charAt(stChar)));
            }
        }

        return sb.toString();
    }
}
