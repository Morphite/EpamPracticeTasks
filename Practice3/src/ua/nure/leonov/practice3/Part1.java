package ua.nure.leonov.practice3;

import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Part1 {

    private static final int ARR_SIZE = 50;

    private Part1() {
        throw new IllegalStateException("Utility class");
    }

    public static String convert1(String input) {
        String temp = input;
        StringBuilder result = new StringBuilder();

        String[] hashName = new String[ARR_SIZE];
        int size = 0;

        String[] strArr = temp.split("\n");

        int index;

        for (String str : strArr) {
            if (str.contains("@")) {
                index = str.indexOf(';');
                hashName[size] = str.substring(0, index);
                size++;
            }
        }

        for (String name : hashName) {
            if (name != null){
                result.append(name).append(": ");
                for (String str : strArr) {
                    if (str.contains(name)) {
                        index = str.lastIndexOf(';');
                        result.append(str, index + 1, str.length()).append("\n");
                    }
                }
            }
        }

        return result.toString();
    }

    public static String convert2(String input) {
        String temp = input;
        StringBuilder result = new StringBuilder();
        String[] hashName = new String[ARR_SIZE];
        int size = 0;

        String[] strArr = temp.split("\n");

        int index;
        int secondIndex;

        for (String str : strArr) {
            if (str.contains("@")) {
                index = str.indexOf(';');
                secondIndex = str.lastIndexOf(';');
                String tempName = str.substring(index + 1, secondIndex);

                index = tempName.indexOf(' ');
                String firstName = tempName.substring(0, index);
                String secondName = tempName.substring(index + 1, tempName.length());

                tempName = secondName + " " + firstName;
                hashName[size] = tempName;
                size++;
            }
        }

        for (String name : hashName) {
            if(name != null){
                for (String str : strArr) {
                    index = name.indexOf(' ');
                    String tempName;

                    String secondName = name.substring(0, index);
                    String firstName = name.substring(index + 1, name.length());

                    tempName = firstName + " " + secondName;

                    if (str.contains(tempName)) {
                        result.append(name).append(' ');
                        index = str.lastIndexOf(';');
                        result.append("(email: ").append(str, index + 1, str.length()).append(")");
                    }
                }
            }
            result.append("\n");
        }
        return result.toString();
    }


    public static String convert3(String input) {
        StringBuilder sb3 = new StringBuilder();
        String regex = "(?U)(\\w+);(\\w+ \\w+);(\\w+)@(\\w+\\.\\w+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(input);
        while (m.find()) {
            String domen = m.group(4);
            String user = m.group(1);
            if (posDomain(sb3.toString(),domen)==-1) {
                sb3.append(domen).append(" ==> ").append(user).append(System.lineSeparator());
            } else {
                sb3.insert(posDomain(sb3.toString(),domen),", "+user);
            }
        }
        return sb3.toString();
    }

    private static int posDomain(String s, String domain) {
        int pos = -1;
        String regex = "(?U)("+domain+" ==> \\w+)\\s";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        if (m.find())	{
            pos = m.end(1);
        }
        return pos;
    }

    public static String convert4(String input) {
        SecureRandom rnd = new SecureRandom();
        StringBuilder s = new StringBuilder("Login;Name;Email;Password" + System.lineSeparator());
        String regex = "(?U)(\\w+);(\\w+ \\w+);(\\w+)@(\\w+\\.\\w+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher m = pattern.matcher(input);
        while (m.find()) {
            s.append(input, m.start(), m.end()).append(";");
            for (int i = 0; i < 4; i++) {
                s.append(rnd.nextInt(10));
            }
            s.append(System.lineSeparator());
        }

        return s.toString();
    }
}
