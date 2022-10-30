package ru.job4j.cash;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.assertj.core.api.Assertions.assertThat;

class CacheTest {

    @Test
    void whenAdd() {
        Cache cache = new Cache();
        Assertions.assertTrue(cache.add(new Base(1, 1, "First")));
        Assertions.assertFalse(cache.add(new Base(1, 1, "First")));
    }

    @Test
    void whenDelete() {
        Cache cache = new Cache();
        Base model = new Base(1, 1, "First");
        cache.add(model);
        cache.delete(model);
        Assertions.assertTrue(cache.add(model));
    }

    @Test
    void whenUpdate() {
        Cache cache = new Cache();
        Base model = new Base(1, 1, "First");
        cache.add(model);
        model.setName("First1");
        Assertions.assertTrue(cache.update(model));
    }

    @Test
    void whenNotUpdate() throws OptimisticException {
        Cache cache = new Cache();
        Base model = new Base(1, 1, "First");
        cache.add(model);
        Throwable thrown = catchThrowable(() -> cache.update(new Base(1, 2, "First")));
        assertThat(thrown).isInstanceOf(OptimisticException.class);
        Assertions.assertEquals(thrown.getMessage(), "Versions are not equal");
    }
}