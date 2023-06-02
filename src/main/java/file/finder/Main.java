package file.finder;

import ru.job4j.io.ArgsName;
import ru.job4j.io.Search;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
    private List<Path> list = new ArrayList<>();

    public Main() {
    }

    /**
     * Валидация входных параметров
     * используется класс ArgsName
     * @see ArgsName
     */
    private void finder(String[] arg) throws IOException {
        ArgsName argsName = ArgsName.of(arg);
        Path start = Path.of(argsName.get("d"));
        String type = argsName.get("t");
        String logFile = argsName.get("o");
        String sign = argsName.get("n");

        if (!start.toFile().isDirectory()) {
            throw new IllegalArgumentException("Директория начала поиска не существует");
        }
        if (!(type.equals("mask") || type.equals("name") || type.equals("regex"))) {
            throw new IllegalArgumentException("Тип поиск определен некорректно");
        }
        if (!logFile.endsWith(".txt")) {
            throw new IllegalArgumentException("Ошибка файла записи");
        }
        /**
         * Не ищет файлы вне пакета !!!
         */
        if (type.equals("mask")) {
            Pattern pattern = Pattern.compile(mask(sign));
            list.addAll(Search.search(start, p -> pattern.matcher(p.toFile().getName()).matches()));
        } else if (type.equals("name")) {
            list.addAll(Search.search(start, p -> p.toFile().getName().equals(sign)));
        } else if (type.equals("regex")) {
            Pattern pattern = Pattern.compile(sign);
            list.addAll(Search.search(start, p -> pattern.matcher(p.toFile().getName()).matches()));
        }
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(logFile)
                ))) {
            for (Path str : list) {
                out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * В методе mask() мы передаем маску имени файла "n", а на выходе получаем регулярное выражение
     */
    private static String mask(String name) {
        String replaceDot = name.replace(".", "\\.");
        String replaceQ =  replaceDot.replace("?", "\\w?");
        String replaceS = replaceQ.replace("*", "\\w+");
        return replaceS;
    }

    public static void main(String[] args) throws IOException {
        Main first = new Main();
        first.finder(args);
    }
}
