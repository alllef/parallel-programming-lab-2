package com.github.alllef.algorithm.fox_algo;

import com.github.alllef.algorithm.MatrixMultiplying;

import java.util.ArrayList;
import java.util.List;

public class FoxMatrixMultiplying implements MatrixMultiplying {
    private final int processesNum;

    public FoxMatrixMultiplying(int processesNum) {
        this.processesNum = processesNum;
    }

    @Override
    public synchronized int[][] multiply(int[][] firstMatr, int[][] secondMatr) {
        int[][] resultMatr = new int[firstMatr.length][secondMatr[0].length];
        int matrSize = firstMatr.length;
        int iterNum = (int) Math.sqrt(processesNum);
        int subMatrSize = matrSize / iterNum;
        Counter counter = new Counter(processesNum);

        List<FoxProcess> processes = startAndGetProcesses(iterNum,counter);

        for (int iter = 0; iter < iterNum; iter++) {
            for (int process = 0; process < processes.size(); process++) {
                FoxProcess foxProcess = processes.get(process);
                int k = (foxProcess.getRow() + iter) % iterNum;
                int[][] firstPartitionMatr = getPartitionMatr(firstMatr, foxProcess.getRow(), k, subMatrSize);
                int[][] secondPartitionMatr = getPartitionMatr(secondMatr, k, foxProcess.getCol(), subMatrSize);
                foxProcess.setMatrices(firstPartitionMatr, secondPartitionMatr);
            }

            counter.waitUntilIterEnd();

            for (FoxProcess process : processes)
                copyPartitionMatr(resultMatr, process.getResultMatr(), process.getRow(), process.getCol());
        }

        return resultMatr;
    }

    private List<FoxProcess> startAndGetProcesses(int iterNum, Counter counter) {
        List<FoxProcess> processes = new ArrayList<>();
        for (int i = 0; i < iterNum; i++) {
            for (int j = 0; j < iterNum; j++) {
                FoxProcess process = new FoxProcess(iterNum, i, j, counter);
                Thread thread = new Thread(process);
                thread.start();
                processes.add(process);
            }
        }
        return processes;
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
        int subMatrSize = partitionMatr.length;
        for (int row = 0; row < subMatrSize; row++) {
            for (int col = 0; col < subMatrSize; col++) {
                resultMatr[partRow * subMatrSize + row][partCol * subMatrSize + col] += partitionMatr[row][col];
            }
        }
    }
}