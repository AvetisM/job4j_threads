package ru.job4j.cash;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class CacheTest {

    @Test
    public void whenAdd() {
        Cache cache = new Cache();
        Assertions.assertTrue(cache.add(new Base(1, 1, "First")));
        Assertions.assertFalse(cache.add(new Base(1, 1, "First")));
    }

    @Test
    public void whenDelete() {
        Cache cache = new Cache();
        Base model = new Base(1, 1, "First");
        cache.add(model);
        cache.delete(model);
        Assertions.assertTrue(cache.add(model));
    }

    @Test
    public void whenUpdate() {
        Cache cache = new Cache();
        Base model = new Base(1, 1, "First");
        cache.add(model);
        model.setName("First1");
        Assertions.assertTrue(cache.update(model));
    }

    @Test
    public void whenNotUpdate() throws OptimisticException {
        Cache cache = new Cache();
        Base model = new Base(1, 1, "First");
        cache.add(model);
        try {
            cache.update(new Base(1, 2, "First"));
        } catch (OptimisticException op) {
            Assertions.assertNotEquals("", op.getMessage());
        }
    }
}