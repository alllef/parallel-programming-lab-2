package com.github.alllef.algorithm;

import com.github.alllef.MatrixUtils;
import com.github.alllef.algorithm.fox_algo.FoxMatrixMultiplying;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.alllef.algorithm.TestUtils.parseToMatr;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class FoxMatrixMultiplyingTests {

    @ParameterizedTest
    @MethodSource("matrProvider")
    void multiplyTest(int[][] firstMatr, int[][] secondMatr) {
        MatrixMultiplying testedMatrixMultiplying = new FoxMatrixMultiplying(4);
        MatrixMultiplying simpleMatrixMatrixMultiplying = new SimpleMatrixMultiplying();
        int[][] expectedResultMatr = simpleMatrixMatrixMultiplying.multiply(firstMatr, secondMatr);
        int[][] actualResultMatr = testedMatrixMultiplying.multiply(firstMatr, secondMatr);

        for (int i = 0; i < actualResultMatr.length; i++)
            assertArrayEquals(expectedResultMatr[i], actualResultMatr[i]);
    }

    protected static Stream<Arguments> matrProvider() {
        return Stream.of(
                arguments(
                        MatrixUtils.generateMatrix(64),
                      MatrixUtils.generateMatrix(64)
                        ),
                arguments(
                        MatrixUtils.generateMatrix(128),
                        MatrixUtils.generateMatrix(128)
                )
        );
    }
}
