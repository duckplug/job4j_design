package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path file : sources) {
                zip.putNextEntry(new ZipEntry(file.toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validateArgs(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Ошибка в количестве аргументов");
        }
        ArgsName argsname = ArgsName.of(args);
        File file  = new File(argsname.get("d"));
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("Директория не существует - " + argsname.get("d"));
        }
        if (!argsname.get("e").startsWith(".") && argsname.get("e").length() > 1) {
            throw new IllegalArgumentException("Ошибка расширения - " + argsname.get("e"));
        }
        if (!(argsname.get("o").endsWith(".zip") && argsname.get("o").length() > 4)) {
            throw new IllegalArgumentException("ошибка в создании архива - " + argsname.get("o"));
        }
    }

    public static void main(String[] args) throws IOException {
        validateArgs(args);
        ArgsName argsname = ArgsName.of(args);
        List<Path> list =   Search.search(Paths.get(argsname.get("d")), p -> !p.toFile().getName().endsWith(argsname.get("e")));
        validateArgs(args);
        Zip zip = new Zip();
        zip.packFiles(list, new File(argsname.get("o")));
    }
}