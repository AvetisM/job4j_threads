package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    private final Object monitor = this;
    private int size;

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (monitor) {
                while (queue.size() == size) {
                    monitor.wait();
                }
                queue.offer(value);
                monitor.notifyAll();
        }
    }

    public T poll() throws InterruptedException {
        T rls;
        synchronized (monitor) {
                while (queue.isEmpty()) {
                    monitor.wait();
                }
                rls = queue.poll();
        }
        return rls;
    }

}
