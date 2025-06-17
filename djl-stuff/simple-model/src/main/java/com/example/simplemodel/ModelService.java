package com.example.simplemodel;

import ai.djl.*;
import ai.djl.basicdataset.cv.classification.Mnist;
import ai.djl.nn.*;
import ai.djl.nn.core.*;
import ai.djl.training.DefaultTrainingConfig;
import ai.djl.training.Trainer;
import ai.djl.training.evaluator.Accuracy;
import ai.djl.training.listener.TrainingListener;
import ai.djl.training.loss.Loss;
import ai.djl.training.util.ProgressBar;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ModelService {
    private final long inputSize = 28*28;
    private final long outputSize = 10;

    Application application = Application.CV.IMAGE_CLASSIFICATION;

    public Model createModel() {
        SequentialBlock sequentialBlock = new SequentialBlock();
        sequentialBlock.add(Blocks.batchFlattenBlock(inputSize));
        sequentialBlock.add(Linear.builder().setUnits(128).build());
        sequentialBlock.add(Activation::relu);
        sequentialBlock.add(Linear.builder().setUnits(64).build());
        sequentialBlock.add(Activation::relu);
        sequentialBlock.add(Linear.builder().setUnits(outputSize).build());

        Model model = Model.newInstance("mlp");
        model.setBlock(sequentialBlock);
        return model;
    }

    public Mnist createDataset() throws IOException {
        int batchSize = 32;
        Mnist mnist = Mnist.builder().setSampling(batchSize, true).build();
        mnist.prepare(new ProgressBar());
        return mnist;
    }

    public Trainer createTrainer(Model model) {
        DefaultTrainingConfig config = new DefaultTrainingConfig(Loss.softmaxCrossEntropyLoss())
                .addEvaluator(new Accuracy())
                .addTrainingListeners(TrainingListener.Defaults.logging());
        return model.newTrainer(config);
    }

}
