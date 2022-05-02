package com.github.alllef.algorithm.striped_algo;

public class StripedBlockProcess implements Runnable {
    private final int subTaskNum;
    private final int[] firstMatrRow;
    private final int[][] secondMatr;
    private final int[][] resultMatr;

    public StripedBlockProcess(int subTaskNum, int[] firstMatrRow, int[][] secondMatr, int[][] resultMatr) {
        super();
        this.subTaskNum = subTaskNum;
        this.firstMatrRow = firstMatrRow;
        this.secondMatr = secondMatr;
        this.resultMatr = resultMatr;
    }

    @Override
    public void run() {
        int iterNum = secondMatr[0].length;
        int columnNum = firstMatrRow.length;

        for (int currIter = 0; currIter < iterNum; currIter++) {
            int currSecondMatrCol = subTaskNum - currIter;
            if (currSecondMatrCol < 0)
                currSecondMatrCol = iterNum - (-currSecondMatrCol);

            for (int currCol = 0; currCol < columnNum; currCol++) {
                resultMatr[subTaskNum][currSecondMatrCol] += firstMatrRow[currCol] * secondMatr[currCol][currSecondMatrCol];
            }
            System.out.println(subTaskNum+" "+currSecondMatrCol);
        }
    }
}
