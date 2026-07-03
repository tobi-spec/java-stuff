package com.example.springhandlefiles;

import java.io.File;
import java.io.IOException;

public class FileIO {
    /**
     * It is important to note that the File class cannot modify or access the contents of the file it represents.
     */
    public static void main(String[] args) throws IOException {
        File file = new File("testfile.txt");
        file.createNewFile();
        System.out.println(file.getAbsoluteFile());
        file.delete();

        File dir = new File("testdir");
        dir.mkdir();
        System.out.println(dir.getAbsoluteFile());
        dir.delete();

    }
}
