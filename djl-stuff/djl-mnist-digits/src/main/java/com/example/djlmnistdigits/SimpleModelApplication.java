package com.example.djlmnistdigits;

import ai.djl.Model;
import ai.djl.modality.cv.ImageFactory;
import ai.djl.ndarray.types.Shape;
import ai.djl.training.EasyTrain;
import ai.djl.training.Trainer;
import ai.djl.training.dataset.Dataset;
import ai.djl.translate.TranslateException;
import ai.djl.translate.Translator;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SimpleModelApplication {


    public static void main(String[] args) throws IOException, TranslateException {
        ModelService modelService = new ModelService();

        Dataset mnist = modelService.createDataset();
        Model model = modelService.createModel();
        Trainer trainer = modelService.createTrainer(model);

        trainer.initialize(new Shape(1, 28*28));
        EasyTrain.fit(trainer, 2, mnist, null);

        var img = ImageFactory.getInstance().fromUrl("https://resources.djl.ai/images/0.png");
        img.getWrappedImage();

        Translator translator = new CustomTranslator();
        var predictor = model.newPredictor(translator);
        var classifications = predictor.predict(img);
        System.out.println(classifications);
    }

}
