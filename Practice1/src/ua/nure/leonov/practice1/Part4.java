package ua.nure.leonov.practice1;

public class Part4 {
    private static void usage() {
        System.out.println("Usage: java " + "ua.khpi.demo.task01.Subtask01 X Y");
    }

    public static int nod(int x, int y) {
        while (x != y) {
            if (x > y){
                x -= y;
            } else{
                y -= x;
            }
        }
        return x;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            usage();
            return;
        }
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        int nod = nod(x, y);
        System.out.println(nod);
    }
}