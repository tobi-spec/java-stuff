package com.example.djldrawboundingboxes;

import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.modality.cv.output.BoundingBox;
import ai.djl.modality.cv.output.DetectedObjects;
import ai.djl.modality.cv.output.Rectangle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

;

public class DjlDrawBoundingBoxesApplication {

    public static void main(String[] args) throws IOException {
        Path imagePath = Path.of("djl-stuff/djl-draw-bounding-boxes/src/main/resources/id_card.jpg");
        Image image = ImageFactory.getInstance().fromFile(imagePath);

        List<String> labels = new ArrayList<>();
        List<Double> probabilities = new ArrayList<>();
        List<BoundingBox> detections = new ArrayList<>();

        float[][] xyxyBoxes = {
                {50f, 30f, 200f, 180f},
                {220f, 100f, 350f, 300f}
        };

        for (float[] box: xyxyBoxes) {
            float x = box[0]/ image.getWidth();
            float y = box[1] / image.getHeight();
            float width = (box[2] - box[0]) / image.getWidth();
            float height = (box[3] - box[1]) / image.getHeight();

            BoundingBox rectangle = new Rectangle(x, y, width, height);
            detections.add(rectangle);
            labels.add("Object");
            probabilities.add(0.95); // Example confidence score
        }

        DetectedObjects objects = new DetectedObjects(labels, probabilities, detections);
        image.drawBoundingBoxes(objects);

        Path outputDir = Paths.get("build/output");
        Files.createDirectories(outputDir);

        Path imagePath2 = outputDir.resolve("id_card.png");
        image.save(Files.newOutputStream(imagePath2), "png");

        System.out.println("Image with bounding boxes saved to: " + outputDir.toAbsolutePath());
    }

}
