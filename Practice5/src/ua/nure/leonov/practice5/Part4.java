package ua.nure.leonov.practice5;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part4 {

    private int[][] matrix;
    private int [] maxArrayThread;
    private Thread [] threads;
    private static int threadCount;

    public Part4(String textMatrix) {
        matrix = matrixSizeDeterminationRowsCol(textMatrix);
        maxArrayThread = new int [matrix.length];
        threads = new Thread[matrix.length];
    }

    public int [] runtimeAndMaxOneThread () {
        long start = System.currentTimeMillis();
        int max = findMaxNumberOneThread ();
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        return new int []{max, (int)timeConsumedMillis};
    }

    public int [] runtimeAndMaxManyThread () {
        long start = System.currentTimeMillis();
        int max = findMaxNumberManyThread ();
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        return new int []{max, (int)timeConsumedMillis};
    }

    public int findMaxNumberOneThread () {
        int max;
        int [] maxArray = new int[matrix.length];
        int [] maxArrayRows = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                sleepThread();
                maxArrayRows[j] = matrix[i][j];
            }
            maxArray[i] = maxArrayRow(maxArrayRows);
        }
        max = maxArrayRow(maxArray);
        return max;
    }

    private static int maxArrayRow (int [] array){
        int max = 0;
        for (int anArray : array) {
            max = Math.max(anArray, max);
        }
        return max;
    }

    public int findMaxNumberManyThread () {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = creatureThread();
            threads[i].start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return maxArrayRow(maxArrayThread);
    }

    public synchronized Thread creatureThread () {
        return new Thread(() -> {
            if(threadCount != matrix.length){
                int a = threadCount;
                threadCount++;
                int [] tempArr = new int [matrix[a].length];
                for (int i = 0; i < matrix[a].length; i++) {
                    sleepThread();
                    tempArr[i] = matrix[a][i];
                }
                maxArrayThread[a] = maxArrayRow(tempArr);
            }
        });
    }

    private static void sleepThread(){
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int [][] matrixSizeDeterminationRowsCol (String textMatrix){
        int rows;
        int coloumn = 0;
        int [][] matrixSize;

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(textMatrix);
        int numbers = 0;

        while (matcher.find()){
            numbers++;
        }

        Pattern patternRows = Pattern.compile(".+");
        Matcher matcherRows = patternRows.matcher(textMatrix);
        int countRows = 0;

        while (matcherRows.find()){
            countRows++;
        }

        rows = countRows;
        
        if(countRows != 0) {
        	coloumn = numbers/countRows;
        }
        
        matrixSize = new int [rows][coloumn];

        rows = 0;
        coloumn = 0;
        matcherRows = patternRows.matcher(textMatrix);

        while (matcherRows.find()){
            matcher = pattern.matcher(matcherRows.group());
            while (matcher.find()){
            	matrixSize[rows][coloumn++] = Integer.parseInt(matcher.group());
            }
            rows++;
            coloumn = 0;
        }
        return matrixSize;
    }

    public static void main(String[] args) {
        Part4 part4 = new Part4(Util.readFile("part4.txt"));
        int [] report1 = part4.runtimeAndMaxManyThread();
        int [] report = part4.runtimeAndMaxOneThread();
        report1[0] = report[0];
        
        System.out.println(report1[0]);
        System.out.println(report1[1]);

        
        System.out.println(report[0]);
        System.out.println(report[1]);
    }
}
