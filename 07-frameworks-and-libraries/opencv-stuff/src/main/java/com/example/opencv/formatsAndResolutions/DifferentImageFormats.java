package com.example.opencv.formatsAndResolutions;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

public class DifferentImageFormats {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture camera = new VideoCapture(0); // Open default camera
        Mat frame = new Mat();
        if (camera.read(frame)) {
            Imgcodecs.imwrite("output.png", frame);  // ca. 300 kb
            Imgcodecs.imwrite("output.jpg", frame);  // ca. 50 kb
            Imgcodecs.imwrite("output.bmp", frame);  // ca. 900 kb
            Imgcodecs.imwrite("output.webp", frame); // ca. 150 kb
        }
        camera.release();
    }
}
