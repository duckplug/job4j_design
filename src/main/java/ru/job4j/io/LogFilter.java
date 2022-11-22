package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {

            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] str = line.split(" ");
                if (str[str.length - 2].equals("404") && !str[str.length - 1].equals("-")) {

                    rsl.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rsl;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);

    }
}