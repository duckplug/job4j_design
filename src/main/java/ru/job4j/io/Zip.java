package ru.job4j.io;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        sources = Search.search(, p -> p.toFile().getName().endsWith(args[1]))


    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Ошибка в количестве аргументов");
        }
        ArgsName argsName = ArgsName.of(args);
        File file  = new File(argsName.get("d"));
        if (!file.isDirectory()) {
            throw new IllegalArgumentException("Директория - " + args[0] + " не существует");
        }
        System.out.println(argsName.get("e"));



//        Zip zip = new Zip();
//        zip.packSingleFile(
//                new File("./README.md"),
//                new File("./pom.zip")
//        );
    }
}