package com.example.opencv.formatsAndResolutions;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

public class DifferentJpegCompression {
    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        
        VideoCapture camera = new VideoCapture(0);
        if(!camera.isOpened()) {
            throw new IllegalStateException("Camera not opend");// Open default camera
        }
        
        Mat image = new Mat();
        if (!camera.read(image)) {
            throw new IllegalStateException("No frame captured from camera");
        }

        saveJpeg("output1.jpg", image, 95);
        saveJpeg("output2.jpg", image, 10);

        camera.release();
        image.release();
    }

    private static void saveJpeg(String image, Mat frame, int quality) {
        MatOfInt params = new MatOfInt(Imgcodecs.IMWRITE_JPEG_QUALITY, quality);
        Imgcodecs.imwrite(image, frame, params);
        params.release();
    }
}
