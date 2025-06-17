package com.example.djlmnistdigits;

import java.util.*;
import java.util.stream.*;

import ai.djl.ndarray.*;
import ai.djl.modality.*;
import ai.djl.modality.cv.*;
import ai.djl.modality.cv.util.NDImageUtils;
import ai.djl.translate.*;

public class CustomTranslator implements Translator {

    @Override
    public NDList processInput(TranslatorContext context, Object o) throws Exception {
        var input = (Image) o;
        NDArray array = input.toNDArray(context.getNDManager(), Image.Flag.GRAYSCALE);
        return new NDList(NDImageUtils.toTensor(array));
    }

    @Override
    public Classifications processOutput(TranslatorContext context, NDList list) {
        NDArray probabilities = list.singletonOrThrow().softmax(0);
        List<String> classNames = IntStream.range(0, 10).mapToObj(String::valueOf).collect(Collectors.toList());
        return new Classifications(classNames, probabilities);
    }

    @Override
    public Batchifier getBatchifier() {
        return Batchifier.STACK;
    }


}
