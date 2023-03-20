package ru.job4j.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        /**
         * Валидация параметров (надо сделать)
         */

        String[] firstLine = new String[100];
            /**
             * разбиваем на отдельные значения фильтр
             */
            String[] filters = argsName.get("filter").split(",");
            /**
             * Считываем первую строку в массив
             */

            try (var scanner = new Scanner(new FileReader(argsName.get("path")))) {
                firstLine = scanner.next().split(argsName.get("delimiter"));
            } catch (IOException e) {
                e.printStackTrace();
            }


        /**
         * Создаем лист в котором будут позиции столбцов согласно фильтру;
         * Заполняем его номерами ячеек
         */
        ArrayList<Integer> position = new ArrayList<>();
        for (int i = 0; i < filters.length; i++) {
            for (int j = 0; j < firstLine.length; j++) {
                if (filters[i].equals(firstLine[j])) {
                    position.add(j);
                }
            }
        }
        /**
         * Проверка источника вывода данных;
         */
        if (argsName.get("out").equals("stdout")) {
            /**
             * Блок вывода данных в консоль
             */
            StringJoiner sjFilters = new StringJoiner(argsName.get("delimiter"));
            for (String s : filters) {
                sjFilters.add(s);
            }

            try (var scanner = new Scanner(new FileReader(argsName.get("path")))) {
                while (scanner.hasNext()) {
                    StringJoiner sjStd = new StringJoiner(argsName.get("delimiter"));
                    String[] next = scanner.next().split(argsName.get("delimiter"));
                    for (Integer i : position) {
                        sjStd.add(next[i]);
                    }
                    System.out.println(sjStd);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            /**
             * Блок записи данных в файл
             */
        } else {
            try (FileWriter out = new FileWriter(argsName.get("out"))) {
                StringJoiner sj = new StringJoiner(argsName.get("delimiter"));
                var scanner = new Scanner(new FileReader(argsName.get("path")));
                for (String s : filters) {
                    sj.add(s);
                }
                while (scanner.hasNext()) {
                    StringJoiner sjFile = new StringJoiner(argsName.get("delimiter"));
                    String[] next = scanner.next().split(argsName.get("delimiter"));
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