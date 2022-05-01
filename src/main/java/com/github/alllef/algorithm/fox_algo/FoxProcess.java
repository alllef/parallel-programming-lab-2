package com.github.alllef.algorithm.fox_algo;

import com.github.alllef.algorithm.MatrixMultiplying;
import com.github.alllef.algorithm.SimpleMatrixMultiplying;

public class FoxProcess implements Runnable {
    private final int row;
    private final int col;
    private int[][] firstMatr;
    private int[][] secondMatr;
    private int[][] resultMatr;
    private boolean isIterEnd = true;
    private final int iterNum;
    private final Counter counter;
    private MatrixMultiplying simpleMatrixMultiplying = new SimpleMatrixMultiplying();

    public FoxProcess(int iterNum, int row, int col, Counter counter) {
        super();
        this.iterNum = iterNum;
        this.row = row;
        this.col = col;
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int currIter = 0; currIter < iterNum; currIter++)
            calcMatrices();
    }

    private synchronized void calcMatrices() {
        while (isIterEnd) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        resultMatr = simpleMatrixMultiplying.multiply(firstMatr, secondMatr);
        counter.addIter();
        isIterEnd = true;
    }

    public synchronized void setMatrices(int[][] firstMatr, int[][] secondMatr) {
        this.firstMatr = firstMatr;
        this.secondMatr = secondMatr;
        isIterEnd = false;
        notifyAll();
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
