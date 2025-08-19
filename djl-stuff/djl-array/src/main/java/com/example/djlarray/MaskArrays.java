package com.example.djlarray;

import ai.djl.ndarray.NDArray;
import ai.djl.ndarray.NDArrays;
import ai.djl.ndarray.NDList;
import ai.djl.ndarray.NDManager;

import java.util.ArrayList;
import java.util.List;

public class MaskArrays {

    public static void main(String[] args) {
        try(NDManager manager = NDManager.newBaseManager()){
            var array1 = manager.create(new int[]{2,2,6,6});
            var mask = array1.lt(3);

            var array2 = manager.create(new int[]{1,2,3,4});
            var maskedArray = array2.booleanMask(mask);
            System.out.println("Original Array: " + array1);
            System.out.println("Mask: " + mask);
            System.out.println("Masked Array: " + maskedArray);

            var array3 = manager.create(new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 16}
            });

            NDList rows = new NDList();
            for(int i=0; i < mask.toBooleanArray().length; i++){
                if(!mask.toBooleanArray()[i]) {
                    rows.add(array3.get(i));
                }
            }
            NDArray filtered = NDArrays.stack(rows);
            System.out.println(filtered);
        }
    }
}
