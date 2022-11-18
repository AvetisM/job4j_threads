package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class IndexParallelSearch<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final int from;
    private final int to;
    private final T value;

    public IndexParallelSearch(T[] array, int from, int to, T value) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.value = value;
    }

    @Override
    protected Integer compute() {
        if (to - from < 10) {
            return IndexSearch.getIndex(array, from, to, value);
        }
        int mid =  (from + to) / 2;
        IndexParallelSearch<T> firstSearch = new IndexParallelSearch<>(array, from, mid, value);
        IndexParallelSearch<T> secondSearch = new IndexParallelSearch<>(array, mid + 1, to, value);
        firstSearch.fork();
        secondSearch.fork();
        return Math.max(firstSearch.join(), secondSearch.join());
    }

    public static <T> int search(T[] array, T value) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new IndexParallelSearch<>(array, 0, array.length - 1, value));
    }
}
