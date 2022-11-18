package ru.job4j.pool;

import java.util.Objects;

public class IndexSearch<T> {
    public static <T> int getIndex(T[] array, int from, int to, T value) {
        int rls = -1;
        for (int i = from; i <= to; i++) {
            if (Objects.equals(value, array[i])) {
                rls = i;
                break;
            }
        }
        return rls;
    }
}
