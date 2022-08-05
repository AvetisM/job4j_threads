package ru.job4j.io;

import java.io.*;
import java.util.function.Predicate;

public class ParseFile {
    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    public synchronized File getFile() {
        return file;
    }

    public String getContent(Predicate<Character> filter) throws IOException {
        try (BufferedInputStream i = new BufferedInputStream(new FileInputStream(file))) {
            String output = "";
            int data;
            while ((data = i.read()) > 0) {
                if (filter.test((char) data)) {
                    output += (char) data;
                }
            }
            return output;
        }
    }

}
