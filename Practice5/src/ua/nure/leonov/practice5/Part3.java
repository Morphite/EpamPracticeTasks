package ua.nure.leonov.practice5;

import java.util.ArrayList;
import java.util.List;

public class Part3 {

    private int nThreads;
    private int kCycle;
    private int t;

    private List<Thread> threads = new ArrayList<>();
    private Counter counter = new Counter(0, 0);

    public Part3(int nThreads, int kCycle, int t) {
        this.nThreads = nThreads;
        this.kCycle = kCycle;
        this.t = t;
    }

    public void test() {
        for (int j = 0; j < nThreads; j++) {
            Thread thread = new Thread(() -> {
                for (int i = 0; i < kCycle; i++) {
                    System.out.printf("%s %s%n", counter.firstCounter, counter.secondCounter);
                    counter.firstCounter++;
                    try {
                        Thread.sleep(t);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    counter.secondCounter++;
                }
            });
            thread.start();
            threads.add(thread);
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void testSync() {
        for (int j = 0; j < nThreads; j++) {
            new Thread(() -> {
                for (int i = 0; i < kCycle; i++) {
                    synchronized (counter) {
                        System.out.printf("%s %s%n", counter.firstCounter, counter.secondCounter);
                        counter.firstCounter++;
                        try {
                            Thread.sleep(t);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        counter.secondCounter++;
                    }
                }
            }).start();
        }
    }

    public void reset() {
        counter.reset();
    }

    public static void main(String[] args) {
        Part3 p = new Part3(4, 3, 50);
        p.test();
        p.reset();
        p.testSync();
    }

    public static class Counter {
        private int firstCounter;
        private int secondCounter;

        public Counter(int firstCounter, int secondCounter) {
            this.firstCounter = firstCounter;
            this.secondCounter = secondCounter;
        }

        public void reset() {
            firstCounter = 0;
            secondCounter = 0;
        }
    }
}
