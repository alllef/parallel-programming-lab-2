package com.github.alllef.algorithm.fox_algo;

import com.github.alllef.algorithm.MatrixMultiplying;
import com.github.alllef.algorithm.SimpleMatrixMultiplying;

import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class FoxThread extends Thread {
    private final int row;
    private final int col;
    private int[][] firstMatr;
    private int[][] secondMatr;
    private int[][] resultMatr;
    private boolean isIterEnd = true;
    private final int iterNum;

    private MatrixMultiplying simpleMatrixMultiplying = new SimpleMatrixMultiplying();

    public FoxThread(int iterNum, int row,int col) {
        super();
        this.iterNum = iterNum;
        this.row=row;
        this.col=col;
    }

    @Override
    public void run() {
        for (int currIter = 0; currIter < iterNum; currIter++) {
            calcMatrices();
        }
    }

    private void calcMatrices() {
        while (isIterEnd) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        resultMatr = simpleMatrixMultiplying.multiply(firstMatr, secondMatr);
        isIterEnd = true;
    }

    public void setMatrices(int[][] firstMatr, int[][] secondMatr) {
        this.firstMatr = firstMatr;
        this.secondMatr = secondMatr;
        isIterEnd = false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public boolean isIterEnd() {
        return isIterEnd;
    }

    public int[][] getResultMatr() {
        return resultMatr;
    }
}