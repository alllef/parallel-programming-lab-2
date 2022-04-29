package com.github.alllef.algorithm;

import java.util.stream.IntStream;

public class TestUtils {

    public static int[][] parseToMatr(int[] arr, int rows, int cols) {
        int[][] parsedArr = new int[rows][cols];
        int counter = 0;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                parsedArr[row][col] = arr[counter];
                counter++;
            }
        }
        return parsedArr;
    }
}
