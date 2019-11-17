package ua.nure.leonov.practice3;

import java.security.NoSuchAlgorithmException;

public class Demo {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        Part1.convert1("part1.txt");
        Part1.convert2(Util.readFile("part1.txt"));
        System.out.println(Part1.convert3(Util.readFile("part1.txt")));
        Part1.convert4(Util.readFile("part1.txt"));
        Part2.convert("part2.txt");
        Part3.convert(Util.readFile("part3.txt"));
        Part4.hash("ffdasa", "SHA-256");
        Part5.main(new String[] {});
        Part6.main(new String[] {});
    }

}
