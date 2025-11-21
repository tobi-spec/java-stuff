package com.example.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class ReadMat {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat loadedImage = Imgcodecs.imread("spring-stuff/spring-handle-files/src/main/resources/example.jpg");
        System.out.println(loadedImage);
    }



}
