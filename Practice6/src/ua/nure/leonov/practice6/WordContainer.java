package ua.nure.leonov.practice6;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

public class WordContainer {

    private static HashMap<String, Word> wordHashMap = new HashMap();

    static void read() {
        Scanner in = new Scanner(System.in);

        while (in.hasNext()) {
            String temp = in.next();
            if (temp.equals("stop"))
                break;
            else if (wordHashMap.containsKey(temp)) {
                int freq = wordHashMap.get(temp).getFrequency();
                wordHashMap.get(temp).setFrequency(++freq);
            } else wordHashMap.put(temp, new Word(temp, 1));
        }

        System.out.println(wordHashMap);
        for (Map.Entry<String, Word> entry: wordHashMap.entrySet()) {
            System.out.print(entry.getValue().getContent() + " " + ": " + entry.getValue().getFrequency());
            System.out.println();
        }

    }

    public static void main(String[] args) {
        read();
    }
}
