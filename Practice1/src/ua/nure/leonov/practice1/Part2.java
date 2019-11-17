package ua.nure.leonov.practice1;

public class Part2 {
    public static void main(String[] args) {
        int result = 0;
        for (String str: args){
            result += Integer.parseInt(str);
        }


        System.out.println(result);
    }
}