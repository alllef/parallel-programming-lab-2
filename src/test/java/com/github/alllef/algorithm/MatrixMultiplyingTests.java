package com.github.alllef.algorithm;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.alllef.algorithm.TestUtils.parseToMatr;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class MatrixMultiplyingTests {

    protected abstract MatrixMultiplying getMatrixMultiplying();

    @ParameterizedTest
    @MethodSource("matrProvider")
    void multiplyTest(int[][] firstMatr, int[][] secondMatr, int[][] expectedResultMatr) {
        MatrixMultiplying matrixMultiplying = getMatrixMultiplying();
        int[][] actualResultMatr = matrixMultiplying.multiply(firstMatr, secondMatr);

        for (int i = 0; i < actualResultMatr.length; i++)
            assertArrayEquals(expectedResultMatr[i], actualResultMatr[i]);
    }

    protected abstract Stream<Arguments> matrProvider();
}
