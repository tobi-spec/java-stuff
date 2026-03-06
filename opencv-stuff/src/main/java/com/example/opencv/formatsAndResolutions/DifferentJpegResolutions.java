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

        VideoCapture camera = new VideoCapture(0);
        if(!camera.isOpened()) {
            throw new IllegalStateException("Camera not opend");// Open default camera
        }

        Mat frame = new Mat();
        if (!camera.read(frame)) {
            throw new IllegalStateException("No frame captured from camera");
        }

        System.out.println("Resolution: " + frame.cols() + "x" + frame.rows());
        Imgcodecs.imwrite("output.jpg", frame);  // ca. 50 kb

        resizeAndSave(frame, 1280, 720, "output2.jpg");
        resizeAndSave(frame, 1920, 1080, "output3.jpg");

        frame.release();
        camera.release();
    }

    private static void resizeAndSave(Mat frame, int width, int height, String image) {
        Mat imageForResize = frame.clone();
        Imgproc.resize(imageForResize, imageForResize, new Size(width, height));
        System.out.println("Resolution: " + imageForResize.cols() + "x" + imageForResize.rows());
        Imgcodecs.imwrite(image, imageForResize);
        imageForResize.release();
    }
}
