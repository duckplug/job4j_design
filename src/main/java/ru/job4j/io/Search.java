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
            throw new IllegalArgumentException("ошибка расширения файла - " + args[1]);
        }
        Path path = Path.of(args[0]);
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException("путь до каталога - " + args[0] + " не найден");
        }
        if (!path.toFile().isDirectory()) {
            throw new IllegalArgumentException("каталог - " + args[0] + " не существует");
        }
    }
}

