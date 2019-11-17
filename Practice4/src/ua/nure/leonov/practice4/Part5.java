package ua.nure.leonov.practice4;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {

    private static final String FILE_NAME = "resources";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in,"cp1251");
        while (sc.hasNext()) {
            try {
                String[] arr = sc.nextLine().split(" ");
                if ("stop".equalsIgnoreCase(arr[0])) {
                    break;
                }
                if (arr.length != 2) {
                    throw new ArrayIndexOutOfBoundsException();
                }
                Locale locale = new Locale(arr[1].toLowerCase(Locale.ENGLISH));
                ResourceBundle rb = ResourceBundle.getBundle(FILE_NAME, locale);
                System.out.println(rb.getString(arr[0]));
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("No such values");
            } catch (MissingResourceException e) {
                System.out.println("Invalid key");
            }
        }
        sc.close();
    }
}
