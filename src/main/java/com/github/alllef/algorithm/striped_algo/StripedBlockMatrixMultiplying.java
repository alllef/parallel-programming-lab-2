package com.github.alllef.algorithm.striped_algo;

import com.github.alllef.algorithm.MatrixMultiplying;

public class StripedBlockMatrixMultiplying implements MatrixMultiplying {

    @Override
    public int[][] multiply(int[][] firstMatr, int[][] secondMatr) {
        int[][] resultMatr = new int[firstMatr.length][secondMatr[0].length];
        for (int matrRow = 0; matrRow < firstMatr.length; matrRow++){
            Thread stripedBlockThread = new StripedBlockThread(matrRow,firstMatr[matrRow],secondMatr,resultMatr);
            stripedBlockThread.start();
        }
            return resultMatr;
    }

}
