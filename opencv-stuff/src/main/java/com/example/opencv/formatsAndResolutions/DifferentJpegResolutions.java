package com.example.opencv.formatsAndResolutions;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;

public class DifferentJpegResolutions {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture camera = new VideoCapture(0); // Open default camera
        Mat frame = new Mat();
        if (camera.read(frame)) {
            System.out.println("Resolution: " + frame.cols() + "x" + frame.rows());
            Imgcodecs.imwrite("output.jpg", frame);  // ca. 50 kb

            Mat imageForResize = frame.clone();
            Imgproc.resize(imageForResize, imageForResize, new Size(1280, 720));
            System.out.println("Resolution: " + imageForResize.cols() + "x" + imageForResize.rows());
            Imgcodecs.imwrite("output2.jpg", imageForResize); // 100kb
            imageForResize.release();

            Mat imageForResize2 = frame.clone();
            Imgproc.resize(imageForResize2, imageForResize2, new Size(1920, 1080));
            System.out.println("Resolution: " + imageForResize2.cols() + "x" + imageForResize2.rows());
            Imgcodecs.imwrite("output3.jpg", imageForResize2); // 200kb
            imageForResize2.release();
        }
        frame.release();
        camera.release();
    }
}
