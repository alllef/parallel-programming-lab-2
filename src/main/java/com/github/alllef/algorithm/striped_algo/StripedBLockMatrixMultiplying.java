package com.github.alllef.algorithm.striped_algo;

import com.github.alllef.algorithm.MatrixMultiplying;

import java.util.ArrayList;
import java.util.List;

public class StripedBLockMatrixMultiplying implements MatrixMultiplying {

    @Override
    public int[][] multiply(int[][] firstMatr, int[][] secondMatr) {
        int[][] resultMatr = new int[firstMatr.length][secondMatr[0].length];

        List<Thread> threads = new ArrayList<>();

        for (int matrRow = 0; matrRow < firstMatr.length; matrRow++) {
            Thread stripedBlockThread = new StripedBlockThread(matrRow, firstMatr[matrRow], secondMatr, resultMatr);
            stripedBlockThread.start();
            threads.add(stripedBlockThread);
        }

        for (Thread thread:threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

            return resultMatr;
    }

}
