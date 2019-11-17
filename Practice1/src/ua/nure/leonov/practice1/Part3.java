package ua.nure.leonov.practice1;

public class Part3 {
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (String s: args) {
            if (s != null){
                sb.append(s).append(" ");
            }
        }
        sb.deleteCharAt(sb.lastIndexOf(" "));
        System.out.print(sb.toString());
    }
}