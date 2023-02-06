package ru.job4j.io.duplicates;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (values.get(key) == null) {
            throw new IllegalArgumentException("нет совпадений");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("нет аргументов");
        }
        for (String pare : args) {
           int keyStart =  pare.indexOf("-");
           int keyEnd = pare.indexOf("=");
           if (keyStart == -1 || keyEnd == -1 || keyEnd == pare.length() - 1 || keyEnd
               + keyStart == 1 || pare.endsWith("-")) {
               throw new IllegalArgumentException("неправильный шаблон ключ-значение");
           }
           String key = pare.substring(keyStart + 1, keyEnd);
           String value = pare.substring(keyEnd  + 1);
           values.put(key, value);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));
        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}