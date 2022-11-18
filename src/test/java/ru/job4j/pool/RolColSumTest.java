package ru.job4j.pool;

import org.junit.jupiter.api.Test;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

class RolColSumTest {

    @Test
    public void asyncCalculation() throws ExecutionException, InterruptedException {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 11, 12, 13}, {14, 15, 16, 17}};
        RolColSum.Sums[] sums = RolColSum.asyncSum(matrix);
        assertThat(sums[0].rowSum).isEqualTo(10);
        assertThat(sums[0].colSum).isEqualTo(29);
        assertThat(sums[1].rowSum).isEqualTo(26);
        assertThat(sums[1].colSum).isEqualTo(34);
        assertThat(sums[2].rowSum).isEqualTo(45);
        assertThat(sums[2].colSum).isEqualTo(38);
        assertThat(sums[3].rowSum).isEqualTo(62);
        assertThat(sums[3].colSum).isEqualTo(42);
    }

    @Test
    public void simpleCalculation() {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 11, 12, 13}, {14, 15, 16, 17}};
        RolColSum.Sums[] sums = RolColSum.sum(matrix);
        assertThat(sums[0].rowSum).isEqualTo(10);
        assertThat(sums[0].colSum).isEqualTo(29);
        assertThat(sums[1].rowSum).isEqualTo(26);
        assertThat(sums[1].colSum).isEqualTo(34);
        assertThat(sums[2].rowSum).isEqualTo(45);
        assertThat(sums[2].colSum).isEqualTo(38);
        assertThat(sums[3].rowSum).isEqualTo(62);
        assertThat(sums[3].colSum).isEqualTo(42);
    }
}