package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("нет совпадений по ключу - " + key);
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String pare : args) {
            checkValidation(pare);
            int keyStart = pare.indexOf("-");
            int keyEnd = pare.indexOf("=");
            String key = pare.substring(keyStart + 1, keyEnd);
            String value = pare.substring(keyEnd + 1);
            values.put(key, value);
        }
    }

    private void checkValidation(String arg) {
        int keyStart =  arg.indexOf("-");
        int keyEnd = arg.indexOf("=");
        if (keyStart == -1 || keyEnd == -1 || keyEnd == arg.length() - 1 || keyEnd
                + keyStart == 1 || arg.endsWith("-")) {
            throw new IllegalArgumentException("неправильный шаблон ключ-значение");
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("нет аргументов");
        }
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