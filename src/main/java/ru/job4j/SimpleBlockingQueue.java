package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    private final Object monitor = this;

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public void offer(T value) {
        synchronized (monitor) {
            queue.offer(value);
            notifyAll();
        }
    }

    public T poll() {
        T rls = null;
        synchronized (monitor) {
            rls = queue.poll();
            notifyAll();
        }
        return rls;
    }
}
