package com.github.alllef;

public class MatrixUtils {
    public static int[][] copyMatr(int[][] matr) {
        int[][] copiedMatr = new int[matr.length][matr[0].length];
        for (int i = 0; i < copiedMatr.length; i++) {
            System.arraycopy(matr[i], 0, copiedMatr[i], 0, copiedMatr[0].length);
        }
        return copiedMatr;
    }
}
