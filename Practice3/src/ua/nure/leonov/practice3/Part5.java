package ua.nure.leonov.practice3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part5 {

    private static final int INT5 = 5;
    private static final int INT20 = 20;
    private static final int INT40 = 40;
    private static final int INT50 = 50;
    private static final int INT90 = 90;
    private static final int INT100 = 100;

    public static void main(String[] args) {
        for (int i = 1; i <= INT100; i += 1) {
            show(i);
        }
    }

    public static String decimal2Roman(int x) {

        StringBuilder res = new StringBuilder();

        if (x >= INT40 && x < INT90) {
            res.append("L");
        } else if (x >= INT90) {
            res.append("C");
        }

        if ((x % INT50) / 10 < 4) {
            for (int i = 0; i < (x % INT50) / 10; i++) {
                res.append("X");
            }
        } else {
            res.insert(0, "X");
        }

        int ost10 = x % 10;
        int ost5 = x % 5;

        if (ost10 >= 4 && ost10 < 9) {
            res.append("V");
        } else if (ost10 == 9) {
            res.append("X");
        }

        if (ost5 < 4) {
            for (int i = 0; i < ost5; i++) {
                res.append("I");
            }
        } else {
            res.insert(res.toString().length() - 1, "I");
        }
        return res.toString();
    }

    public static int roman2Decimal(String s) {
        int x = 0;

        String regex100 = "C";
        Pattern p100 = Pattern.compile(regex100);
        Matcher m100 = p100.matcher(s);
        if (m100.find()) {
            x += INT100;
        }

        String regex50 = "L";
        Pattern p50 = Pattern.compile(regex50);
        Matcher m50 = p50.matcher(s);
        if (m50.find()) {
            x += INT50;
        }

        String regexV = "V";
        Pattern pV = Pattern.compile(regexV);
        Matcher mV = pV.matcher(s);
        if (mV.find()) {
            x += INT5;
        }

        Pattern pX = Pattern.compile("(X{1,3})");
        Matcher mX = pX.matcher(s);
        while (mX.find()) {
            x += mX.group(1).length() * 10;
        }

        String regexM10 = "\\AX[CL]";
        Pattern pM10 = Pattern.compile(regexM10);
        Matcher mM10 = pM10.matcher(s);
        if (mM10.find()) {
            x -= INT20;
        }

        String regex1 = "(I+)\\b";
        Pattern p1 = Pattern.compile(regex1);
        Matcher m1 = p1.matcher(s);
        if (m1.find()) {
            x += m1.group(1).length();
        }

        String regexM1 = "IX\\b";
        Pattern pM1 = Pattern.compile(regexM1);
        Matcher mM1 = pM1.matcher(s);
        if (mM1.find()) {
            x -= 1;
        }

        String regex4 = "IV";
        Pattern p4 = Pattern.compile(regex4);
        Matcher m4 = p4.matcher(s);
        if (m4.find()) {
            x -= 1;
        }
        return x;
    }

    public static void show(int x) {
        String romanS = decimal2Roman(x);
        int restoreX = roman2Decimal(romanS);
        System.out.println(x + " --> " + romanS + "-->" + restoreX);
    }
}
