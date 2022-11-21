package ru.job4j.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

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
            futures.put(k,  calculateRowCol(matrix, k));
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

    public static CompletableFuture<Sums> calculateRowCol(int[][] matrix, int index) {
        return CompletableFuture.supplyAsync(() -> {
                    Sums sums = new Sums(0, 0);
                    sums.colSum = getColSum(matrix, index);
                    sums.rowSum = getRowSum(matrix, index);
                    return sums;
                }
        );
    }
}
