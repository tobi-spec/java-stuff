package com.example.djlarray;

import ai.djl.ndarray.NDManager;
import ai.djl.ndarray.types.DataType;
import ai.djl.ndarray.types.Shape;

public class ShapeArrays {

    public static void main(String[] args) {

        var shape = new Shape(2, 3, 4);
        System.out.println("Shape: " + shape);

        try (NDManager manager = NDManager.newBaseManager()) {
            var array1 = manager.randomInteger(0, 9, new Shape(3, 3, 3), DataType.INT8);
            System.out.println("array1: " + array1);

            var baseArray = manager.create(new int[]{1,2,3,4,5,6,7,8,9,10});
            var reshapedArray = baseArray.reshape(new Shape(2, 5));
            System.out.println("baseArray: " + baseArray);
            System.out.println("reshapedArray: " + reshapedArray);
        }
    }
}
