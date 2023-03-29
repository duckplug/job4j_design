package ru.job4j.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.util.*;

public class CSVReader {
    private static void validateParameters(ArgsName argsName) {
        File filePath = new File(argsName.get("path"));
        File fileOut = new File(argsName.get("out"));
        if (!filePath.exists()) {
            throw new IllegalArgumentException("Файл не существует");
        }
        if (!argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException("Задан неверный формат файла");
        }
        if (fileOut.exists() && !fileOut.getName().endsWith(".csv")) {
            throw new IllegalArgumentException("Неверно задан формат файла вывода");
        }
    }

    public static void handle(ArgsName argsName) {
        String strDelimiter = argsName.get("delimiter");
        String[] filters = argsName.get("filter").split(",");
        List<String> firstLine = new ArrayList<>();
        ArrayList<Integer> position = new ArrayList<>();
        validateParameters(argsName);
        try (var scanner = new Scanner(new FileReader(argsName.get("path")))) {
            firstLine = Arrays.asList(scanner.next().split(strDelimiter));
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String s : filters) {
            if (firstLine.contains(s)) {
                position.add(firstLine.indexOf(s));
            }
        }
        if (position.isEmpty()) {
            throw new IllegalArgumentException("Нет совпадений по параметру filter");
        }
        StringJoiner rsl = new StringJoiner(System.lineSeparator());
        try (var scanner = new Scanner(new FileReader(argsName.get("path"))).useDelimiter(System.lineSeparator())) {
            while (scanner.hasNext()) {
                StringJoiner str = new StringJoiner(strDelimiter);
                String[] next = scanner.next().split(strDelimiter);
                for (Integer i : position) {
                    str.add(next[i]);
                }
                rsl.add(str.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (("stdout").equals(argsName.get("out"))) {
            System.out.println(rsl);
        } else {
            try (PrintWriter out = new PrintWriter(argsName.get("out"))) {
                out.println(rsl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        CSVReader.handle(argsName);
    }
}