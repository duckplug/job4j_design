package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) throws IOException {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path file : sources) {
                zip.putNextEntry(new ZipEntry(file.toString()));
            }
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Path> fillSources(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        return Search.search(Paths.get(argsName.get("d")), p -> p.toFile().getName().endsWith(argsName.get("e")));
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 3) {
            throw new IllegalArgumentException("Ошибка в количестве аргументов");
        }
        ArgsName argsName = ArgsName.of(args);
        File file  = new File(argsName.get("d"));
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("Директория - " + args[0] + " не существует");
        }

        Zip zip = new Zip();
        zip.packFiles(fillSources(args), new File("./pom.zip")
        );
    }
}