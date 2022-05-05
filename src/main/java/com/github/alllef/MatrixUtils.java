package com.github.alllef;

import java.util.Random;

public class MatrixUtils {
    public static int[][] copyMatr(int[][] matr) {
        int[][] copiedMatr = new int[matr.length][matr[0].length];
        for (int i = 0; i < copiedMatr.length; i++) {
            System.arraycopy(matr[i], 0, copiedMatr[i], 0, copiedMatr[0].length);
        }
        return copiedMatr;
    }

    public static int[][] generateMatrix(int matrixSize) {
        int[][] matrix = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrix[i][j] = new Random().nextInt(10);
            }
        }
        return matrix;
    }
}
