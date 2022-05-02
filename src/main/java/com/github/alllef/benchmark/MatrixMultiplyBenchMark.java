package com.github.alllef.benchmark;

import com.github.alllef.algorithm.MatrixMultiplying;
import com.github.alllef.algorithm.SimpleMatrixMultiplying;
import com.github.alllef.algorithm.fox_algo.FoxMatrixMultiplying;
import com.github.alllef.algorithm.striped_algo.StripedBlockMatrixMultiplying;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class MatrixMultiplyBenchMark {
    private static List<Integer> threadsNum = List.of(4, 16);
    private static List<Integer> matrixSizes = List.of(16, 64, 256);

    private void calculate(List<Integer> threadNums, List<Integer> matrixSizes) {
        try (CSVWriter csvWriter = new CSVWriter(new FileWriter("results.csv"))) {
            StatefulBeanToCsv<ResultsBean> converter = new StatefulBeanToCsvBuilder<ResultsBean>(csvWriter)
                    .build();

            for (Integer matrixSize : matrixSizes) {
                ResultsBean serialBean = writeAndGetResults(converter, ResultsBean.MultiplyingType.SERIAL, new SimpleMatrixMultiplying(), matrixSize, 1, 1);
                for (Integer threadNum : threadNums) {
                    writeAndGetResults(converter, ResultsBean.MultiplyingType.FOX, new FoxMatrixMultiplying(threadNum), matrixSize, threadNum,serialBean.getTimeInMillisSeconds());
                    writeAndGetResults(converter, ResultsBean.MultiplyingType.STRIPED, new StripedBlockMatrixMultiplying(threadNum), matrixSize, threadNum,serialBean.getTimeInMillisSeconds());
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private ResultsBean writeAndGetResults(StatefulBeanToCsv<ResultsBean> converter, ResultsBean.MultiplyingType type, MatrixMultiplying matrixMultiplying, int matrixSize, int threadNum, double serialResult) {
        int[][] firstMatrix = generateMatrix(matrixSize);
        int[][] secondMatrix = generateMatrix(matrixSize);
        double time = calcSecondTime(() ->
                matrixMultiplying.multiply(firstMatrix, secondMatrix));
        if (threadNum == 1)
            serialResult = time;

        ResultsBean resultsBean = new ResultsBean(type, threadNum, matrixSize, time, calcSpeedup(serialResult, time));

        try {
            converter.write(resultsBean);
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            e.printStackTrace();
        }
        return resultsBean;
    }

    public double calcSecondTime(Runnable runnable) {
        long time = System.currentTimeMillis();
        runnable.run();
        long afterTime = System.currentTimeMillis();
        return (afterTime - time);
    }

    private int[][] generateMatrix(int matrixSize) {
        int[][] matrix = new int[matrixSize][matrixSize];
        for (int i = 0; i < matrixSize; i++) {
            for (int j = 0; j < matrixSize; j++) {
                matrix[i][j] = new Random().nextInt(10);
            }
        }
        return matrix;
    }

    public double calcSpeedup(double sequential, double parallel) {
        return parallel/sequential;
    }

    public static void main(String[] args) {
        MatrixMultiplyBenchMark matrixMultiplyBenchMark = new MatrixMultiplyBenchMark();
        matrixMultiplyBenchMark.calculate(MatrixMultiplyBenchMark.threadsNum, MatrixMultiplyBenchMark.matrixSizes);
    }
}
