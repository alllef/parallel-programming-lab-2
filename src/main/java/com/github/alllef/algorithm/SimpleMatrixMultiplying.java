package com.github.alllef.algorithm;

public class SimpleMatrixMultiplying implements MatrixMultiplying {

    @Override
    public int[][] multiply(int[][] firstMatr, int[][] secondMatr) {
        int[][] newMatr = new int[firstMatr.length][secondMatr[0].length];

        for (int i = 0; i < firstMatr.length; i++) {
            for (int j = 0; j < secondMatr.length; j++) {
                for (int k = 0; k < firstMatr[0].length; k++)
                    newMatr[i][j] += firstMatr[i][k] * secondMatr[k][j];
            }
        }
        return newMatr;
    }
}
