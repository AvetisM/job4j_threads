package ru.job4j;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class CountBarrier {
    private final Object monitor = this;

    @GuardedBy("this")
    private final int total;
    @GuardedBy("this")
    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public synchronized void count() {
        for (int i = 0; i < total; i++) {
            count++;
            monitor.notifyAll();
        }
    }

    public synchronized void await() {
        while (count < total) {
            try {
                monitor.wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        CountBarrier countBarrier = new CountBarrier(10);
        Thread master = new Thread(
                () -> {
                    countBarrier.count();
                    System.out.println(Thread.currentThread().getName() + " started");
                }, "Master");

        Thread slave = new Thread(
                () -> {
                    countBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " started");
                }, "Slave");

        master.start();
        slave.start();
    }
}
