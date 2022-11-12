package ru.job4j.emailservice;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.String.format;

public class EmailNotification {

    private final ExecutorService pool = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        pool.submit(getNewTask(user));
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {
        System.out.printf("Отправлено письмо на адрес %s%n", email);
    }

    private Runnable getNewTask(User user) {
        return () -> {
            String subject =
                    format("Notification %s to email %s", user.getName(), user.getEmail());
            String body =
                    format("Add a new event to %s", user.getName());
            send(subject, body, user.getEmail());
        };
    }

    public static void main(String[] args) {
        User user = new User("User", "A123@yandex.ru");
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.emailTo(user);
        emailNotification.close();
    }
}
