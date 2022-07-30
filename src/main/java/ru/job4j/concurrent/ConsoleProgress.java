package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    public static void main(String[] args) {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        try {
            Thread.sleep(50000);
            progress.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        char[] process = {'-', '\\', '|', '/'};
        try {
            int processIndex = 0;
            while (!Thread.currentThread().isInterrupted()) {
                System.out.print("\r loading... " + process[processIndex++]);
                if (processIndex == process.length) {
                    processIndex = 0;
                }
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.print("\r loading was interrupted.");
        }
    }
}

