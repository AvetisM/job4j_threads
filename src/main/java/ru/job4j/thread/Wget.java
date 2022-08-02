package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;
    private String outPutFileName;

    public Wget(String url, int speed, String outPutFileName) {
        this.url = url;
        this.speed = speed;
        this.outPutFileName = outPutFileName;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(this.url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(this.outPutFileName)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            int downloadData = 0;
            long startTime = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                downloadData += bytesRead;
                if (downloadData >= speed) {
                    long elapsed = System.currentTimeMillis() - startTime;
                    if (elapsed < 1000) {
                        Thread.sleep((1000 - elapsed));
                    }
                    downloadData = 0;
                    startTime = System.currentTimeMillis();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }

    static boolean validateArguments(String[] args) {
        return args.length == 2;
    }

    public static void main(String[] args) throws InterruptedException {
        if (!validateArguments(args)) {
            throw new IllegalArgumentException("Illegal arguments");
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        String outPutFileName = "outputFile";
        int lastIndex = url.lastIndexOf('/');
        if (lastIndex != -1) {
            outPutFileName = url.substring(lastIndex + 1);
        }
        Thread wget = new Thread(new Wget(url, speed, outPutFileName));
        wget.start();
        wget.join();
    }
}
