package basic.exception;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class CheckVSUncheckException {

    public static void main(String[] args) throws FileNotFoundException {
        readFile("myfile.txt");
        printLength(null);
    }

    // checked Exception - checked at compile time
    public static void readFile(String fileName) throws FileNotFoundException {
        FileReader fileReader = new FileReader(fileName);
    }

    // unchecked Exception
    public static void printLength(String myString){
        System.out.println(myString.length());
    }
}
