package com.example.tryWithRessouces;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class main {

    public static void main(String[] args) throws FileNotFoundException {

        DebugBufferedReader bufferedReader = new DebugBufferedReader(new FileReader("com/example/tryWithRessouces/input.txt"));

        try(bufferedReader) {
            String line = bufferedReader.readLine();
            System.out.println(line);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(bufferedReader.isClosed());
    }
}
