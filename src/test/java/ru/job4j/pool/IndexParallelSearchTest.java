package ru.job4j.pool;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.job4j.emailservice.User;

class IndexParallelSearchTest {

    @Test
    public void whenSizeLessThen10() {
        Object[] array = {1, 3, 5, 6, 345, 19};
        Assertions.assertEquals(2, IndexParallelSearch.search(array, 5));
    }

    @Test
    public void whenSizeMoreThen10() {
        Object[] array = {1, 3, 5, 6, 345, 19, 21, 456, 66, 32, 35, 11, 33, 65, 66};
        Assertions.assertEquals(8,
                IndexParallelSearch.search(array, 66));
    }

    @Test
    public void whenCantFindValue() {
        Object[] array = {1, 3, 5, 6, 345, 19, 21, 456, 66, 32, 35, 11, 33, 65, 66};
        Assertions.assertEquals(-1,
                IndexParallelSearch.search(array, 77));
    }

    @Test
    public void whenDifferentDataTypes() {
        User user = new User("Mike", "myEmail");
        Object[] array = {1, "3", 5, 6, "345", 19,
                user, 456, 66, 32, 35, 11, 33, 65, 66};
        Assertions.assertEquals(6,
                IndexParallelSearch.search(array, user));
    }
}