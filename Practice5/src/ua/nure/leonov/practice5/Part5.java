package ua.nure.leonov.practice5;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Part5 {
    private RandomAccessFile file;
    private final String path;
    private final int k;
    private final int step;


    Part5 (String path, int k, int step) {
        this.path = path;
        this.k = k;
        this.step = step;
    }

    public void cleanUp(String path) {
        Path pathFile = Paths.get(path);
        try {
            Files.delete(pathFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeThreads () throws IOException {
        cleanUp(path);
        file = new RandomAccessFile(path, "rw");
        creatureThread(k);
        file.close();
    }

    private void creatureThread (int nameThread) {
        if(nameThread == 0){
            return;
        }
        String name = "" + nameThread;
        Thread t = new Thread(name){
            @Override
            public void run() {
                try {
                    int a = Integer.parseInt(name) - 1;
                    creatureThread(a);
                    randomWriteText(a);
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                }
            }
        };
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void randomWriteText (int pos) throws IOException, InterruptedException {
        int amendment  = System.lineSeparator().getBytes().length;
        file.seek(pos * (step + amendment));

        for (int i = 0; i < step; i++) {
            char ch = (char)('0' + pos);
            Thread.sleep(1);
            file.write(ch);
        }

        file.write(System.lineSeparator().getBytes());
    }

    public void readText () throws IOException {
        file = new RandomAccessFile(path, "r");
        StringBuilder stringBuffer = new StringBuilder();
        int b = file.read();
        while(b != -1){
            stringBuffer.append((char)b);
            b = file.read();
        }
        System.out.println(stringBuffer);
    }

    public static void main(String[] args) throws IOException {
        Part5 part5 = new Part5("part5.txt", 10, 20);

        part5.writeThreads();

        part5.readText();
    }
}
