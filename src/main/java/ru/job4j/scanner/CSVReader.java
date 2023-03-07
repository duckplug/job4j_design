package ru.job4j.scanner;

import ru.job4j.io.ArgsName;

import java.io.*;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        ArrayList<String> list = new ArrayList<>(10);
        var scanner = new Scanner(new FileReader(argsName.get("path")));
        while (scanner.hasNext()) {
           var scan = scanner.next();
            list.add(scan);
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        CSVReader.handle(argsName);
    }
}