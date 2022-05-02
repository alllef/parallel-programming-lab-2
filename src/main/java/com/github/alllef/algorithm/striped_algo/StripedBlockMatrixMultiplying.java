package com.github.alllef.algorithm.striped_algo;

import com.github.alllef.algorithm.MatrixMultiplying;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StripedBlockMatrixMultiplying implements MatrixMultiplying {
    private final int threadNum;

    public StripedBlockMatrixMultiplying(int threadNum) {
        this.threadNum = threadNum;
    }

    @Override
    public int[][] multiply(int[][] firstMatr, int[][] secondMatr) {
        int[][] resultMatr = new int[firstMatr.length][secondMatr[0].length];
        int rowsPerThread = firstMatr.length / threadNum;

        List<Thread> threads = new ArrayList<>();
        List<StripedBlockProcess> processes = new ArrayList<>();

        for (int matrRow = 0; matrRow < firstMatr.length; matrRow++) {
            StripedBlockProcess stripedBlockProcess = new StripedBlockProcess(matrRow, firstMatr[matrRow], secondMatr, resultMatr);
            processes.add(stripedBlockProcess);
            if ((matrRow + 1) % rowsPerThread == 0) {
                List<StripedBlockProcess> finalProcesses = processes;
                Runnable runnable = () -> finalProcesses.forEach(StripedBlockProcess::run);
                Thread stripedBlockThread = new Thread(runnable);
                stripedBlockThread.start();
                threads.add(stripedBlockThread);
                processes = new ArrayList<>();
            }

        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return resultMatr;
    }

}
