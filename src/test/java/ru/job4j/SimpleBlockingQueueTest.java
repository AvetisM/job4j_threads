package ru.job4j;

import org.junit.jupiter.api.Test;

class SimpleBlockingQueueTest {

    @Test
    public void simpleBlockingQueueSize10() throws InterruptedException {
        SimpleBlockingQueue simpleBlock = new SimpleBlockingQueue<>(10);
        Thread producer = new Thread(() -> {
            try {
                simpleBlock.offer(Math.random());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                simpleBlock.poll();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}