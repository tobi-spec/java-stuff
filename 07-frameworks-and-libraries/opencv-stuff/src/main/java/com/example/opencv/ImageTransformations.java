package com.example.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageTransformations {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat image = Imgcodecs.imread("spring-stuff/spring-handle-files/src/main/resources/example.jpg");

        Mat flippedImage = image.clone();
        var rotationMatrix = Imgproc.getRotationMatrix2D(new org.opencv.core.Point(flippedImage.cols()/2, flippedImage.rows()/2), 180, 1);
        Imgproc.warpAffine(flippedImage, flippedImage, rotationMatrix, new Size(flippedImage.cols(), flippedImage.rows()));
        HighGui.imshow("exmaple", flippedImage);
        HighGui.waitKey(0);
    }
}
