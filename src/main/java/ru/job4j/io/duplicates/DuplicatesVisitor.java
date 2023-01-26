package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    public HashMap<FileProperty, Path> map = new HashMap<>();
    public HashMap<String, FileProperty> duplicates = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProp = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (!map.containsKey(fileProp)) {
            map.put(fileProp, file);
        } else {
            duplicates.put(file.toFile().getAbsolutePath(), fileProp);
        }
        return FileVisitResult.CONTINUE;
    }

    public HashMap<String, FileProperty> getDuplicates() {
        return duplicates;
    }
}
