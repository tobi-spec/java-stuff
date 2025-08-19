package com.example.djlarray;

import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDManager;
import ai.djl.ndarray.types.DataType;
import ai.djl.ndarray.types.Shape;

import java.util.Arrays;

public class ArrayToArray {

    public static void main(String[] args) {
        NDManager manager1 = NDManager.newBaseManager();
        NDArray array1 = manager1.create(new int[]{1, 2, 3, 4, 5});

        int[] intArray = array1.toIntArray();
        System.out.println("Converted int array: " + Arrays.toString(intArray));

        NDManager manager2 = NDManager.newBaseManager();
        array1.attach(manager2);
        manager1.close();
        System.out.println("manager switched int array: " + Arrays.toString(intArray));
    }



}
