package com.github.alllef.algorithm;

import com.github.alllef.algorithm.striped_algo.StripedBlockMatrixMultiplying;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static com.github.alllef.algorithm.TestUtils.parseToMatr;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class StripedBlockMatrixMultiplyingTests extends MatrixMultiplyingTests {

    @Override
    protected MatrixMultiplying getMatrixMultiplying() {
        return new StripedBlockMatrixMultiplying(2);
    }

    @Override
    protected Stream<Arguments> matrProvider() {
        return Stream.of(
                arguments(
                        parseToMatr(new int[]{5, 6, 2, 1, 3, 4, 6, 7, 8}, 3, 3),
                        parseToMatr(new int[]{2, 5, 3, 5, 7, 8, 1, 23, 4}, 3, 3),
                        parseToMatr(new int[]{42, 113, 71, 21, 118, 43, 55, 263, 106}, 3, 3)
                ),
                arguments(
                        parseToMatr(new int[]{2, 4, 6, 7, 3, 4}, 3, 2),
                        parseToMatr(new int[]{2, 5, 7, 9, 10, 11, 13, 5}, 2, 4),
                        parseToMatr(new int[]{44, 54, 66, 38, 82, 107, 133, 89, 46, 59, 73, 47}, 3, 4)
                ),
                arguments(
                        parseToMatr(new int[]{5, 6, 8, 8, 2, 4, 6, 7, 2, 5, 4, 7, 3, 4, 6, 7}, 4, 4),
                        parseToMatr(new int[]{2, 5, 7, 7, 8, 9, 3, 4, 7, 9, 2, 3, 5, 6, 8, 3}, 4, 4),
                        parseToMatr(new int[]{154, 199, 133, 107, 113, 142, 94, 69, 107, 133, 93, 67, 115, 147, 101, 76}, 4, 4)
                )
        );
    }
}
