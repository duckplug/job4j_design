package ru.job4j.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        String strDelimiter = argsName.get("delimiter");
        String[] filters = argsName.get("filter").split(",");
        List<String> firstLine = new ArrayList<>();
        ArrayList<Integer> position = new ArrayList<>();
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
        if (argsName.get("out").equals("stdout")) {
            StringJoiner sjFilters = new StringJoiner(strDelimiter);
            for (String s : filters) {
                sjFilters.add(s);
            }
            try (var scanner = new Scanner(new FileReader(argsName.get("path")))) {
                while (scanner.hasNext()) {
                    StringJoiner sjStd = new StringJoiner(strDelimiter);
                    String[] next = scanner.next().split(strDelimiter);
                    for (Integer i : position) {
                        sjStd.add(next[i]);
                    }
                    System.out.println(sjStd);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try (FileWriter out = new FileWriter(argsName.get("out"));
                 var scanner = new Scanner(new FileReader(argsName.get("path"))).useDelimiter(System.lineSeparator())) {
                StringJoiner sj = new StringJoiner(strDelimiter);
                for (String s : filters) {
                    sj.add(s);
                }
                while (scanner.hasNext()) {
                    StringJoiner sjFile = new StringJoiner(strDelimiter);
                    String[] next = scanner.next().split(strDelimiter);
                    for (Integer i : position) {
                        sjFile.add(next[i]);
                    }
                    out.write(sjFile.toString());
                    out.write(System.lineSeparator());
                }
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