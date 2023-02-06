package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            throw new IllegalArgumentException("нарушено число параметров");
        }
        validationParameters(args);
        Path start = Paths.get(args[0]);
        search(start, p -> p.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException  {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    private static void validationParameters(String[] args) {
        if (!(args[1].startsWith(".") && args[1].length() > 1)) {
            throw new IllegalArgumentException("ошибка расширения файла");
        }
        Path path = Path.of("D:\\JP\\job4j_design");
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException("путь до каталога не найден");
        }
        if (!path.toFile().isDirectory()) {
            throw new IllegalArgumentException("каталог не существует");
        }
    }
}

