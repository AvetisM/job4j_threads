package ru.job4j.pool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.job4j.emailservice.User;

import java.util.concurrent.ForkJoinPool;

class IndexParallelSearchTest {

    @Test
    public void whenSizeLessThen10() {
        Object[] array = {1, 3, 5, 6, 345, 19};
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer actual =
                (Integer) forkJoinPool.invoke(
                        new IndexParallelSearch(array, 0, array.length - 1, 5));
        Assertions.assertEquals(2, actual);
    }

    @Test
    public void whenSizeMoreThen10() {
        Object[] array = {1, 3, 5, 6, 345, 19, 21, 456, 66, 32, 35, 11, 33, 65, 66};
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer actual =
                (Integer) forkJoinPool.invoke(
                        new IndexParallelSearch(array, 0, array.length - 1, 66));
        Assertions.assertEquals(8, actual);
    }

    @Test
    public void whenCantFindValue() {
        Object[] array = {1, 3, 5, 6, 345, 19, 21, 456, 66, 32, 35, 11, 33, 65, 66};
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer actual =
                (Integer) forkJoinPool.invoke(
                        new IndexParallelSearch(array, 0, array.length - 1, 77));
        Assertions.assertEquals(-1, actual);
    }

    @Test
    public void whenDifferentDataTypes() {
        User user = new User("Mike", "myEmail");
        Object[] array = {1, "3", 5, 6, "345", 19,
                user, 456, 66, 32, 35, 11, 33, 65, 66};
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Integer actual =
                (Integer) forkJoinPool.invoke(
                        new IndexParallelSearch(array, 0, array.length - 1, user));
        Assertions.assertEquals(6, actual);
    }
}