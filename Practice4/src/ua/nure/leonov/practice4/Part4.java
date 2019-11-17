package ua.nure.leonov.practice4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 {

    private static final String INPUT = "part4.txt";

    private static String[] container;

    public static void main(String[] args) {
        start();
    }

    public static void start() {
        String text = null;
        try {
            text = read(INPUT);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        container = cutSentences(text);
        IteratorOfContainer iterator = iterator();
        while (iterator.hasNext()) {
            String temp = iterator.next();
            temp = temp.replaceAll("  ", " ");
            System.out.println(temp);
        }
    }

    public static String[] cutSentences(String text) {

        List<String> sentence = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\S+[^.!?]*[.!?])");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String st = matcher.group(0);
            if (Character.isLowerCase(st.charAt(0))) {
                continue;
            }
            st = st.replaceAll("\n", " ");
            st = st.replaceAll("\r", "");
            sentence.add(st);
        }
        String[] sentences = new String[sentence.size()];
        for (int i = 0; i < sentences.length; i++) {
            sentences[i] = sentence.get(i);
        }
        return sentences;
    }

    private static String read(String path) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();

        try (Scanner scanner = new Scanner(new File(path), "CP1251")) {
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }

    private static IteratorOfContainer iterator() {
        return new IteratorOfContainer();
    }

    private static class IteratorOfContainer implements Iterator<String> {

        private int index;

        IteratorOfContainer() {
            index = -1;
        }

        @Override
        public boolean hasNext() {
            return index < container.length - 1;
        }

        @Override
        public String next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return container[++index];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
