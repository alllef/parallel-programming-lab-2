package com.github.alllef.algorithm.fox_algo;

import com.github.alllef.algorithm.MatrixMultiplying;

import java.util.ArrayList;
import java.util.List;

public class FoxMatrixMultiplying implements MatrixMultiplying {

    @Override
    public int[][] multiply(int[][] firstMatr, int[][] secondMatr) {
        int[][] resultMatr = new int[firstMatr.length][secondMatr[0].length];
        int partitionSize = 4;
        int iterNum = (int) Math.sqrt(partitionSize);
        List<FoxThread> threads = new ArrayList<>();

        for (int i = 0; i < iterNum; i++) {
            for (int j = 0; j < iterNum; j++) {
                FoxThread thread = new FoxThread(iterNum, i, j);
                thread.start();
                threads.add(thread);
            }
        }

        for (int iter = 0; iter < iterNum; iter++) {
            for (int thread = 0; thread < threads.size(); thread++) {
                FoxThread foxThread = threads.get(thread);
                int k = (foxThread.getRow() + 1) % iterNum;
                int[][] firstPartitionMatr = getPartitionMatr(firstMatr, foxThread.getRow(), k, partitionSize);
                int[][] secondPartitionMatr = getPartitionMatr(secondMatr, k, foxThread.getCol(), partitionSize);
                foxThread.setMatrices(firstPartitionMatr, secondPartitionMatr);
            }
            while (!isEnded(threads)) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            for (FoxThread thread : threads)
                copyPartitionMatr(resultMatr, thread.getResultMatr(), thread.getRow(), thread.getCol());
        }

        return resultMatr;
    }

    private boolean isEnded(List<FoxThread> threads) {
        for (FoxThread thread : threads)
            if (!thread.isIterEnd()) return false;

        return true;
    }

    private int[][] getPartitionMatr(int[][] originMatr, int partRow, int partCol, int partitionSize) {
        int[][] partitionMatr = new int[partitionSize][partitionSize];
        for (int row = 0; row < partitionSize; row++) {
            for (int col = 0; col < partitionSize; col++) {
                partitionMatr[row][col] = originMatr[partRow * partitionSize + row][partCol * partitionSize + col];
            }
        }

        return partitionMatr;
    }

    private void copyPartitionMatr(int[][] resultMatr, int[][] partitionMatr, int partRow, int partCol) {
        int partitionSize = partitionMatr.length;
        for (int row = 0; row < partitionSize; row++) {
            for (int col = 0; col < partitionSize; col++) {
                resultMatr[partRow * partitionSize + row][partCol * partitionSize + col] += partitionMatr[row][col];
            }
        }
    }
}