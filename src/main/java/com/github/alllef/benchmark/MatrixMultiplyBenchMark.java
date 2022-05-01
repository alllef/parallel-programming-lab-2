package com.github.alllef.benchmark;

import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import com.github.alllef.algorithm.MatrixMultiplying;
import com.github.alllef.algorithm.fox_algo.FoxMatrixMultiplying;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatrixMultiplyBenchMark {
    private static List<Integer> threadsNum = List.of(2, 4, 8, 16);
    private static List<Integer> matrixSizes = List.of(16, 64, 256);

    private void calculate(List<Integer> threadsNum, List<Integer> matrixSizes) {
        for (int i = 0; i < threadsNum.size(); i++) {
            for (int j = 0; j < matrixSizes.size(); j++) {
                FoxMatrixMultiplying foxMatrixMultiplying = new FoxMatrixMultiplying(threadsNum.get(i));
                int[][] firstMatrix = generateMatrix(matrixSizes.get(j));
                int[][] secondMatrix = generateMatrix(matrixSizes.get(j));
                double time = calcSecondTime(() ->
                        foxMatrixMultiplying.multiply(firstMatrix, secondMatrix));

            }
        }
    }

    public double calcSecondTime(Runnable runnable) {
        long time = System.currentTimeMillis();
        runnable.run();
        long afterTime = System.currentTimeMillis();
        return (afterTime - time) / 1000d;
    }

    public void writeCsv() {

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
}
