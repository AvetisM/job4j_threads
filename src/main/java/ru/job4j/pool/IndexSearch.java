package ru.job4j.pool;

import java.util.Objects;

public class IndexSearch {
    public static int getIndex(Object[] array, int from, int to, Object value) {
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
