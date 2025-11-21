package com.example.opencv;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.HighGui;
import org.opencv.videoio.VideoCapture;

public class Camera {

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        VideoCapture camera = new VideoCapture(0); // Open default camera
        Mat frame = new Mat();
        if (camera.read(frame)) {
            HighGui.imshow("camera image", frame);
            HighGui.waitKey(0);
        }
        camera.release();
    }
}
