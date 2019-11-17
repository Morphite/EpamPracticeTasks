package ua.nure.leonov.practice1;

public class Part6 {
    public static void main(String[] args) {
        int quantity = Integer.parseInt(args[0]);
        final int size = 50;
        int[] list = new int[size];
        int count = 0;

        if (quantity == 1){
            System.out.print(2);
        } else {
            for (int j = 2; j < list.length; j++){
                if (isPrime(j)){
                    list[count] = j;
                    count++;
                }
            }

            for (int j = 0; j < quantity; j++){
                System.out.print(list[j] + " ");
            }

            System.out.print("\b");
        }
    }

    public static boolean isPrime(int number) {
        for (int check = 2; check < number; ++check) {
            if (number % check == 0) {
                return false;
            }
        }
        return true;
    }
}

