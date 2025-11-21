package com.example.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;

public class HighGUI {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat loadedImage = Imgcodecs.imread("spring-stuff/spring-handle-files/src/main/resources/example.jpg");
        HighGui.imshow("exmaple", loadedImage);
        HighGui.waitKey(0);
    }
}
