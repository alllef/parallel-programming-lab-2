package com.github.alllef.benchmark;

import com.github.alllef.algorithm.fox_algo.FoxMatrixMultiplying;
import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class MatrixMultiplyBenchMark {
    private static List<Integer> threadsNum = List.of(4, 16);
    private static List<Integer> matrixSizes = List.of(16, 64, 256);

    private void calculate(List<Integer> threadNums, List<Integer> matrixSizes) {
        CSVWriter csvWriter;
        try {
            csvWriter = new CSVWriter(new FileWriter("results.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for (Integer threadNum : threadNums) {
            for (Integer matrixSize : matrixSizes) {
                FoxMatrixMultiplying foxMatrixMultiplying = new FoxMatrixMultiplying(threadNum);
                int[][] firstMatrix = generateMatrix(matrixSize);
                int[][] secondMatrix = generateMatrix(matrixSize);
                double time = calcSecondTime(() ->
                        foxMatrixMultiplying.multiply(firstMatrix, secondMatrix));
                ResultsBean resultsBean = new ResultsBean(ResultsBean.MultiplyingType.FOX, threadNum, matrixSize, calcSpeedup(1, time));
                StatefulBeanToCsv<ResultsBean> converter = new StatefulBeanToCsvBuilder<ResultsBean>(csvWriter)
                        .build();
                try {
                    converter.write(resultsBean);
                } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        try {
            csvWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public double calcSecondTime(Runnable runnable) {
        long time = System.currentTimeMillis();
        runnable.run();
        long afterTime = System.currentTimeMillis();
        return (afterTime - time) / 1000d;
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
        return sequential / parallel;
    }

    public static void main(String[] args) {
        MatrixMultiplyBenchMark matrixMultiplyBenchMark = new MatrixMultiplyBenchMark();
        matrixMultiplyBenchMark.calculate(MatrixMultiplyBenchMark.threadsNum, MatrixMultiplyBenchMark.matrixSizes);
    }
}
