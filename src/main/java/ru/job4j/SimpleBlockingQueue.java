package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.*;

@ThreadSafe
public class SimpleBlockingQueue<T> implements Iterable<T> {

    private final Object monitor = this;
    private int size;

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();

    public SimpleBlockingQueue(int size) {
        this.size = size;
    }

    @Override
    public synchronized Iterator<T> iterator() {
       return copy(queue).iterator();
    }

    List<T> copy(Queue<T> queue) {
        return new LinkedList<>(queue);
    }

    public void offer(T value) {
        synchronized (monitor) {
            try {
                while (queue.size() == size) {
                    monitor.wait();
                }
                queue.offer(value);
                monitor.notifyAll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public T poll() {
        T rls = null;
        synchronized (monitor) {
            try {
                while (queue.isEmpty()) {
                    monitor.wait();
                }
                rls = queue.poll();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return rls;
    }

}
