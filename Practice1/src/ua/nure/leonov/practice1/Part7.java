package ua.nure.leonov.practice1;

public class Part7 {

    public static int str2int(String number) {
        int digit = 0;
        final int num = 64;
        final int powNum = 26;
        for (int i = 1, j = number.length(); j > 0; i++, j--) {
            digit += (number.charAt(number.length() - i) - num)
                    * Math.pow(powNum, (double) number.length() - j);
        }
        return digit;
    }

    public static String int2str(int number) {
        StringBuilder chars = new StringBuilder();
        StringBuilder charsMirror = new StringBuilder();
        int modul;
        int divident = number;
        final int num26 = 26;
        final int num64 = 64;
        while (divident != 0) {
            modul = divident % num26;
            if (modul == 0) {
                chars.append("Z");
                divident = (divident - 1) / num26;
            } else {
                chars.append((char) (modul + num64));
                divident = (divident - modul) / num26;
            }
        }
        for (int i = 0; i < chars.length(); i++){
            charsMirror.append(chars.charAt(chars.length() - i - 1));
        }
        return charsMirror.toString();

    }

    public static String rightColumn(String number) {
        String chars = "";
        int num;
        num = str2int(number);
        num++;
        chars = int2str(num);
        return chars;
    }

    public static void main(String[] args) {
        final String arrow = " ==> ";
        System.out.println("A" + arrow + Part7.str2int("A") + arrow + Part7.int2str(Part7.str2int("A")));
        System.out.println("B" + arrow + Part7.str2int("B") + arrow + Part7.int2str(Part7.str2int("B")));
        System.out.println("Z" + arrow + Part7.str2int("Z") + arrow + Part7.int2str(Part7.str2int("Z")));
        System.out.println("AA" + arrow + Part7.str2int("AA") + arrow + Part7.int2str(Part7.str2int("AA")));
        System.out.println("AZ" + arrow + Part7.str2int("AZ") + arrow + Part7.int2str(Part7.str2int("AZ")));
        System.out.println("BA" + arrow + Part7.str2int("BA") + arrow + Part7.int2str(Part7.str2int("BA")));
        System.out.println("ZZ" + arrow + Part7.str2int("ZZ") + arrow + Part7.int2str(Part7.str2int("ZZ")));
        System.out.println("AAA" + arrow + Part7.str2int("AAA") + arrow + Part7.int2str(Part7.str2int("AAA")));
    }
}