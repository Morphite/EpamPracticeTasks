package ua.nure.leonov.practice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Part1 {

    public static void main(String[] args) {
        convertToUpperCase();
    }

    public static void convertToUpperCase() {
        try (Scanner s = new Scanner(new File("part1.txt"), "CP1251")) {
            StringBuilder sb = new StringBuilder();
            while (s.hasNextLine()) {
                sb.append(s.nextLine()).append(System.lineSeparator());

            }
            Pattern p = Pattern.compile("(?ui)([a-zA-z]*)([а-яA-Я]*)");
            Matcher m = p.matcher(sb);
            while (m.find()) {
                if (m.group(0).length() > 3) {
                    List<Character> charList = m.group(0).chars().mapToObj(c -> (char) c).collect(Collectors.toList());
                    StringBuilder tempSB = new StringBuilder();
                    for (char ch : charList) {
                        if (Character.isLowerCase(ch)) {
                            tempSB.append(String.valueOf(ch).toUpperCase(Locale.ENGLISH));
                        } else {
                            tempSB.append(String.valueOf(ch).toLowerCase(Locale.ENGLISH));
                        }
                    }
                    sb.replace(m.start(), m.end(), tempSB.toString());
                }

            }
            System.out.println(sb.toString().substring(0, sb.length() - 1));
        } catch (FileNotFoundException e) {
            System.out.println("part1.txt" + " not found");
        }
    }
}
