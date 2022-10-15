package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public void increment() {
        Integer current;
        Integer temp;
        do {
             current = get();
             temp = current + 1;
        } while (!count.compareAndSet(current, temp));
    }

    public int get() {
        Integer current = count.get();
        if (current == null) {
            current = 0;
            count.set(current);
        }
        return current;
    }
}