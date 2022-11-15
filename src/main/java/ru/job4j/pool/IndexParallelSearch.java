package ru.job4j.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class IndexParallelSearch extends RecursiveTask<Integer> {

    private final Object[] array;
    private final int from;
    private final int to;
    private final Object value;

    public IndexParallelSearch(Object[] array, int from, int to, Object value) {
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
        IndexParallelSearch firstSearch = new IndexParallelSearch(array, from, mid, value);
        IndexParallelSearch secondSearch = new IndexParallelSearch(array, mid + 1, to, value);
        firstSearch.fork();
        secondSearch.fork();
        return firstSearch.join() == -1 ? secondSearch.join() : firstSearch.join();
    }

    public static int search(Object[] array, Object value) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new IndexParallelSearch(array, 0, array.length - 1, value));
    }
}
