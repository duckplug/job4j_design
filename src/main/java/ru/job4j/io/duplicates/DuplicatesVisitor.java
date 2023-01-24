package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private HashMap<FileProperty, Path> dupplicatesMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProp = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (!dupplicatesMap.containsKey(fileProp)) {
            dupplicatesMap.put(fileProp, file);
        } else {
            System.out.printf("%s - %s\n\r", fileProp.getName(), file.toFile().getAbsolutePath());
        }
        return super.visitFile(file, attrs);
    }
}
