package com.example.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class ImageProcessing {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat image = Imgcodecs.imread("spring-stuff/spring-handle-files/src/main/resources/example.jpg");
        HighGui.imshow("example", image);
        HighGui.waitKey(0);

        // add blur
        Mat imageForBlur = image.clone();
        Imgproc.GaussianBlur(imageForBlur, imageForBlur, new Size(5,5), 0);
        HighGui.imshow("add blur", imageForBlur);
        HighGui.waitKey(0);

        // convert color
        Mat imageForGrayScaling = image.clone();
        Imgproc.cvtColor(imageForGrayScaling, imageForGrayScaling, Imgproc.COLOR_BGR2GRAY);
        HighGui.imshow("convert color", imageForGrayScaling);
        HighGui.waitKey(0);

        // add threshold
        Mat imageForThreshold = imageForGrayScaling.clone();
        Imgproc.threshold(imageForThreshold, imageForThreshold, 128, 255, Imgproc.THRESH_BINARY);
        HighGui.imshow("add blur", imageForThreshold);
        HighGui.waitKey(0);

        // shows edges
        Mat imageForCanny = image.clone();
        Mat resultCanny = new Mat();
        Imgproc.Canny(imageForCanny, resultCanny, 100, 200);
        HighGui.imshow("add canny", resultCanny );
        HighGui.waitKey(0);

        // resize
        Mat imageForResize = image.clone();
        Imgproc.resize(imageForResize, imageForResize, new Size(100, 100));
        HighGui.imshow("add canny", imageForResize);
        HighGui.waitKey(0);
    }
}
