package com.example.springhandlefiles;

import java.io.*;

public class InputStreamIO {
    /**
     * Nativ Java way to read file from any disk location
     */
    public static void main(String[] args) throws IOException {
        try (FileInputStream inputStream = new FileInputStream("spring-stuff/spring-handle-files/src/main/resources/example.txt")) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        };

    }
}
