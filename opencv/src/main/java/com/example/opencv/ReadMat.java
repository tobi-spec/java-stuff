package com.example.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import java.util.Arrays;

public class ReadMat {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat loadedImage = Imgcodecs.imread("spring-stuff/spring-handle-files/src/main/resources/example.jpg");
        System.out.println("Row of Matrice: " + loadedImage.row(0));
        System.out.println("Column of Matrice: " + loadedImage.col(0));
        System.out.println("Channels: " + loadedImage.channels());
        System.out.println("Values of Pixel: " + Arrays.toString(loadedImage.get(0, 0)));
    }



}
