package com.github.alllef.benchmark;

public class ResultsBean {
    public static enum MultiplyingType {
        FOX, STRIPED, SERIAL
    }

    MultiplyingType multiplyingType;
    private int threadsNumber;
    private int matrixSize;
    private double speedup;

    public ResultsBean(MultiplyingType multiplyingType, int threadsNumber, int matrixSize, double speedup) {
        this.multiplyingType = multiplyingType;
        this.threadsNumber = threadsNumber;
        this.matrixSize = matrixSize;
        this.speedup = speedup;
    }

    public MultiplyingType getMultiplyingType() {
        return multiplyingType;
    }

    public int getThreadsNumber() {
        return threadsNumber;
    }

    public int getMatrixSize() {
        return matrixSize;
    }

    public double getSpeedup() {
        return speedup;
    }

    public void setMultiplyingType(MultiplyingType multiplyingType) {
        this.multiplyingType = multiplyingType;
    }

    public void setThreadsNumber(int threadsNumber) {
        this.threadsNumber = threadsNumber;
    }

    public void setMatrixSize(int matrixSize) {
        this.matrixSize = matrixSize;
    }

    public void setSpeedup(double speedup) {
        this.speedup = speedup;
    }
}
