package com.github.alllef.algorithm;

import com.github.alllef.algorithm.fox_algo.FoxMatrixMultiplying;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.github.alllef.algorithm.TestUtils.parseToMatr;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class FoxMatrixMultiplyingTests extends MatrixMultiplyingTests {
    @Override
    protected MatrixMultiplying getMatrixMultiplying() {
        return new FoxMatrixMultiplying();
    }

    @Override
    protected Stream<Arguments> matrProvider() {
        return Stream.of(
                arguments(
                        parseToMatr(new int[]{5, 6, 8, 8, 2, 4, 6, 7, 2, 5, 4, 7, 3, 4, 6, 7}, 4, 4),
                        parseToMatr(new int[]{2, 5, 7, 7, 8, 9, 3, 4, 7, 9, 2, 3, 5, 6, 8, 3}, 4, 4),
                        parseToMatr(new int[]{154, 199, 133, 107, 113, 142, 94, 69, 107, 133, 93, 67, 115, 147, 101, 76}, 4, 4)
                )
        );
    }
}
