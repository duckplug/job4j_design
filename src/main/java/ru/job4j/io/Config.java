package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;
import java.util.List;
import java.util.stream.Collectors;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            List<String> arr = read.lines().collect(Collectors.toList());
            for (String text : arr) {
                int point = text.indexOf("=");
                if (point < 1 || point == text.length() - 1) {
                    throw new IllegalArgumentException("ошибка шаблона пары ключ - значение");
                }
                String k = text.substring(0, point);
                String v = text.substring(point + 1, text.length());
                values.put(k, v);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Config conf = new Config("./data/app.properties");
        conf.load();
    }
}