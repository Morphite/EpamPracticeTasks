package ua.nure.leonov.practice5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Util {
    private static final String ENCODING = "Cp1251";
    
    Util() {	
    }

    public static String readFile(String path) {
        String res = null;
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(path));
            res = new String(bytes, ENCODING);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return res;
    }
}
