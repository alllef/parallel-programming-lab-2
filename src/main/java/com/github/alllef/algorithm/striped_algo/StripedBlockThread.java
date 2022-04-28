package com.github.alllef.algorithm.striped_algo;

public class StripedBlockThread extends Thread {
    private final int subTaskNum;
    private final int[] firstMatrRow;
    private final int[][] secondMatr;
    private final int[][] resultMatr;

    public StripedBlockThread(int subTaskNum, int[] firstMatrRow, int[][] secondMatr, int[][] resultMatr) {
        super();
        this.subTaskNum = subTaskNum;
        this.firstMatrRow = firstMatrRow;
        this.secondMatr = secondMatr;
        this.resultMatr = resultMatr;
    }

    @Override
    public void run() {
        int columnNum = firstMatrRow.length;

        for (int currIter = 0; currIter < columnNum; currIter++) {
            int currSecondMatrCol = subTaskNum - currIter;
            if (currSecondMatrCol < 0)
                currSecondMatrCol = columnNum - currSecondMatrCol;

            for (int currCol = 0; currCol < columnNum; currCol++)
                resultMatr[subTaskNum][currSecondMatrCol] += firstMatrRow[currCol] * secondMatr[currCol][currSecondMatrCol];
        }
    }
}
