import ai.djl.MalformedModelException;
import ai.djl.inference.Predictor;
import ai.djl.modality.cv.Image;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.modality.cv.output.DetectedObjects;
import ai.djl.modality.cv.output.Rectangle;
import ai.djl.modality.cv.translator.Sam2Translator.Sam2Input;
import ai.djl.modality.cv.translator.Sam2TranslatorFactory;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelNotFoundException;
import ai.djl.repository.zoo.ZooModel;
import ai.djl.training.util.ProgressBar;
import ai.djl.translate.TranslateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class SegmentAnything {

    private static final Logger logger = LoggerFactory.getLogger(SegmentAnything.class);

    private SegmentAnything() {}

    public static void main(String[] args) throws IOException, ModelNotFoundException, MalformedModelException, TranslateException {
        DetectedObjects objects = predict();
        logger.info("Detected objects: {}", objects);
    }

    public static DetectedObjects predict() throws IOException, ModelNotFoundException, MalformedModelException, TranslateException {
        String url = "https://raw.githubusercontent.com/facebookresearch/segment-anything-2/main/notebooks/images/truck.jpg";

        Image image = ImageFactory.getInstance().fromUrl(url);
        Sam2Input input = Sam2Input.builder(image).addPoint(575, 750).addBox(425, 600, 700, 875).build();

        Criteria<Sam2Input, DetectedObjects> criteria =
                Criteria.builder()
                        .setTypes(Sam2Input.class, DetectedObjects.class)
                        .optModelUrls("djl://ai.djl.pytorch/sam2-hiera-tiny")
                        .optTranslatorFactory(new Sam2TranslatorFactory())
                        .optProgress(new ProgressBar())
                        .build();

        try (ZooModel<Sam2Input, DetectedObjects> model = criteria.loadModel();
                Predictor<Sam2Input, DetectedObjects> predictor = model.newPredictor()) {
            DetectedObjects detectedObjects = predictor.predict(input);
            showMask(input, detectedObjects);
            return detectedObjects;
        }
    }

    private static void showMask(Sam2Input input, DetectedObjects detectedObjects) throws IOException {
        Path outputDir = Paths.get("build/output");
        Files.createDirectories(outputDir);

        Image image = input.getImage();
        image.drawBoundingBoxes(detectedObjects, 0.8f);
        image.drawMarks(input.getPoints());
        for(Rectangle rectangle: input.getBoxes()) {
            image.drawRectangle(rectangle, 0xff0000, 6);
        }

        Path imagePath = outputDir.resolve("mask.png");
        image.save(Files.newOutputStream(imagePath), "png");
        logger.info("Segmentation result image has been saved in: {}", imagePath);
    }
}
