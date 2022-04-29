package com.github.alllef.algorithm;

public class SimpleMatrixMultiplying implements MatrixMultiplying {

    @Override
    public int[][] multiply(int[][] firstMatr, int[][] secondMatr) {
        int[][] newMatr = new int[firstMatr.length][secondMatr[0].length];
        int rows =firstMatr.length;
        int cols = secondMatr[0].length;
        int sameRowCols =firstMatr[0].length;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                for (int sameRowCol = 0; sameRowCol < sameRowCols; sameRowCol++)
                    newMatr[row][col] += firstMatr[row][sameRowCol] * secondMatr[sameRowCol][col];
            }
        }
        return newMatr;
    }
}
