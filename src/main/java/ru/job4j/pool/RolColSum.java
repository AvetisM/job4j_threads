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
            sums[k] = getSum(matrix, k);
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

    public static Sums getSum(int[][] matrix, int index) {
        Sums sum = new Sums(0, 0);
        for (int i = 0; i < matrix[index].length; i++) {
            sum.colSum += matrix[i][index];
            sum.rowSum +=  matrix[index][i];
        }
        return sum;
    }

    public static CompletableFuture<Sums> calculateRowCol(int[][] matrix, int index) {
        return CompletableFuture.supplyAsync(() -> getSum(matrix, index)
        );
    }
}
