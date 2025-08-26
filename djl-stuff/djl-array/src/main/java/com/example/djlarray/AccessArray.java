package com.example.djlarray;

import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDManager;
import ai.djl.ndarray.index.NDIndex;
import ai.djl.ndarray.types.Shape;

public class AccessArray {

    public static void main(String[] args) {

        try(NDManager manager = NDManager.newBaseManager()) {
            var complexArray = manager.randomNormal(new Shape(10, 2, 5));

            System.out.println("complexArray: " + complexArray);
            System.out.println("first dimension: " + complexArray.get(0));
            System.out.println("second dimension: " + complexArray.get(0, 0));
            System.out.println("third dimension: " + complexArray.get(0, 0, 0));
            System.out.println("slice" + complexArray.get(new NDIndex("0, 0:2, 0:5")));
            System.out.println("slice" + complexArray.get(new NDIndex("0:6, 0:1, 0:3")));

            NDArray n1Array = manager.create(new int[]{1,2,3,4,5});
            System.out.println(n1Array.getInt(0));
        }


    }


}
