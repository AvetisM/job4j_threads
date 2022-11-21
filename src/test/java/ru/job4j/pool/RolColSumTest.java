package ru.job4j.pool;

import org.junit.jupiter.api.Test;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

class RolColSumTest {

    @Test
    public void asyncCalculation() throws ExecutionException, InterruptedException {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 11, 12, 13}, {14, 15, 16, 17}};
        Sums[] actual = RolColSum.asyncSum(matrix);
        Sums[] expected = new Sums[4];
        expected[0] = new Sums(10, 29);
        expected[1] = new Sums(26, 34);
        expected[2] = new Sums(45, 38);
        expected[3] = new Sums(62, 42);
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void simpleCalculation() {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 11, 12, 13}, {14, 15, 16, 17}};
        Sums[] actual = RolColSum.sum(matrix);
        Sums[] expected = new Sums[4];
        expected[0] = new Sums(10, 29);
        expected[1] = new Sums(26, 34);
        expected[2] = new Sums(45, 38);
        expected[3] = new Sums(62, 42);
        assertThat(actual).isEqualTo(expected);
    }
}