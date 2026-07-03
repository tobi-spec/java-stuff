package com.example.opencv;

import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageAnnotation {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat image = Imgcodecs.imread("spring-stuff/spring-handle-files/src/main/resources/example.jpg");

        Imgproc.line(image, new Point(0, 0), new Point(100, 100), new Scalar(255, 0, 0), 2);
        Imgproc.rectangle(image, new Point(10, 10), new Point(100, 100), new Scalar(0, 255, 0), 3);
        Imgproc.circle(image, new Point(50, 50), 20, new Scalar(0, 0, 255), -1);
        Imgproc.putText(image, "Hello", new Point(10, 50), Imgproc.FONT_HERSHEY_SIMPLEX, 1.0, new Scalar(255, 255, 255));
        HighGui.imshow("example", image);
        HighGui.waitKey(0);




    }
}
