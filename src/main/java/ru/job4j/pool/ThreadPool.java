package ru.job4j.pool;

import ru.job4j.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool {
    int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(size);

    public ThreadPool() {
        init();
    }

    public void work(Runnable job) throws InterruptedException {

    }

    public void shutdown() {
        threads.forEach(Thread::interrupt);
    }

    private void init() {

    }
}
