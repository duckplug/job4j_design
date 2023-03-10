package ru.job4j.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        ArrayList<String> list = new ArrayList<>(10);
        var scanner = new Scanner(new FileReader(argsName.get("path")));
        /**
         * разбиваем на отдельные значения фильтр
         */
        String[] filters = argsName.get("filter").split(",");
        /**
         * Считываем первую строку в массив
         */
         String[] firstLine = scanner.next().split(";");
        /**
         * Создаем лист в котором будут позиции столбцов согласно фильтру;
         * Заполняем его номерами ячеек
         *
         * !!!! Объект из массива filters не равен объекту из массива firstLine !!!!!
         */
        ArrayList<Integer> position = new ArrayList<>();
        for (int i = 0; i < filters.length; i++) {
            for (int j = 0; j < firstLine.length; j++) {
                if (filters[i].equals(firstLine[j])) {
                   position.add(j);
                }
            }
        }

        while (scanner.hasNext()) {
           var scan = scanner.next();
            list.add(scan);
        }
        System.out.println(list);
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        CSVReader.handle(argsName);
    }
}