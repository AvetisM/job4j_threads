package ru.job4j;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private AtomicReference<Integer> count;

    public CASCount(int countValue) {
        this.count = new AtomicReference<>(countValue);
    }

    public void increment() {
        Integer current;
        Integer temp;
        do {
             current = get();
             temp = current + 1;
        } while (!count.compareAndSet(current, temp));
    }

    public int get() {
        return count.get();
    }
}