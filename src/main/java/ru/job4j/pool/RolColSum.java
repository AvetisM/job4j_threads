package ru.job4j.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {
    public static class Sums {
        int rowSum;
        int colSum;

        public Sums(int rowSum, int colSum) {
            this.rowSum = rowSum;
            this.colSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        int n = matrix.length;
        Sums[] sums = new Sums[n];
        for (int k = 0; k < n; k++) {
            Sums sum = new Sums(0, 0);
            sum.rowSum += getRowSum(matrix, k);
            sum.colSum += getColSum(matrix, k);
            sums[k] = sum;
        }
        return sums;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        int n = matrix.length;
        Sums[] sums = new Sums[n];
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int k = 0; k < n; k++) {
            futures.put(k,
                    calculateCol(matrix, k).thenCombine(calculateRow(matrix, k),
                            (r1, r2) -> {
                                Sums sum = new Sums(0, 0);
                                sum.colSum = r1;
                                sum.rowSum = r2;
                                return sum;
                            }
                    )
            );
        }
        for (Integer key : futures.keySet()) {
            sums[key] = futures.get(key).get();
        }
        return sums;
    }

    public static int getColSum(int[][] matrix, int rowIndex) {
        int rls = 0;
        for (int i = 0; i < matrix[rowIndex].length; i++) {
            rls += matrix[i][rowIndex];
        }
        return rls;
    }

    public static int getRowSum(int[][] matrix, int colIndex) {
        int rls = 0;
        for (int j = 0; j < matrix[colIndex].length; j++) {
            rls += matrix[colIndex][j];
        }
        return rls;
    }

    public static CompletableFuture<Integer> calculateRow(int[][] matrix, int colIndex) {
        return CompletableFuture.supplyAsync(() -> getRowSum(matrix, colIndex));
    }

    public static CompletableFuture<Integer> calculateCol(int[][] matrix, int rowIndex) {
        return CompletableFuture.supplyAsync(() -> getColSum(matrix, rowIndex));
    }

}
