package com.example.opencv.formatsAndResolutions;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.videoio.VideoCapture;

public class DifferentJpegCompression2 {
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

        saveJpeg(image, "output_q95.jpg", 95);
        saveJpeg(image, "output_q10.jpg", 10);

        camera.release();
        image.release();
    }

    private static void saveJpeg(Mat image, String fileName, int quality) {
        MatOfInt params = new MatOfInt(Imgcodecs.IMWRITE_JPEG_QUALITY, quality);
        Imgcodecs.imwrite(fileName, image, params);
        params.release();
    }
}