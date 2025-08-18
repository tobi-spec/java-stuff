package com.example.djlarray;

import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDManager;
import ai.djl.ndarray.types.Shape;

public class MakeArrays {

    public static void main(String[] args) {

        try(NDManager manager = NDManager.newBaseManager()) {
            NDArray n1Array = manager.create(new int[]{1,2,3,4,5});
            System.out.println(n1Array);

            NDArray n2Array = manager.create(new int[][]{
                    {1,2,3},
                    {4,5,6}
            });
            System.out.println(n2Array);

            NDArray arangeArray = manager.arange(0, 10);
            System.out.println(arangeArray);

            NDArray randomArray = manager.randomUniform(0, 1, new Shape(5, 5));
            System.out.println(randomArray);
        }
    }
}
