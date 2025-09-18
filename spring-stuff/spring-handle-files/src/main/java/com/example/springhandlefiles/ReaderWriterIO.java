package com.example.springhandlefiles;

import java.io.*;

public class ReaderWriterIO {

    /**
     * Java way to read/write file from class path
     */
    public static void main(String[] args) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));
        writer.write("Hello, World!");
        writer.write("\nThis is a test file.");
        writer.close();

        BufferedReader reader = new BufferedReader(new FileReader("output.txt"));
        String line;
        while((line = reader.readLine()) != null){
            System.out.println(line);
        }
        reader.close();
    }
}
