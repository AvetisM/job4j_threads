package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> { }
        );
        Thread second = new Thread(
                () -> { }
        );
        System.out.println(first.getName());
        System.out.println(second.getName());
        first.start();
        second.start();
        do {
            System.out.println(first.getName() + " " + first.getState());
            System.out.println(second.getName() + " " + second.getState());
        } while (first.getState() != Thread.State.TERMINATED
                && second.getState() != Thread.State.TERMINATED);
        System.out.println(first.getName() + " " + first.getState());
        System.out.println(second.getName() + " " + second.getState());
        System.out.println(Thread.currentThread().getName() + " работа завершнна");
    }
}
