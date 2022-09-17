package ru.job4j;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

class SimpleBlockingQueueTest {

    @Test
    public void simpleBlockingQueueSize10() throws InterruptedException {
        SimpleBlockingQueue simpleBlock = new SimpleBlockingQueue<>(10);
        Thread first = new Thread(() -> simpleBlock.offer(Math.random()));
        Thread second = new Thread(() -> simpleBlock.poll());
        first.start();
        second.start();
        first.join();
        second.join();
        assertTrue(!simpleBlock.iterator().hasNext());
    }
}