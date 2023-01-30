package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    public Map<FileProperty, List<Path>> duplicatesMap = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty fileProp = new FileProperty(file.toFile().length(), file.toFile().getName());
        if (!duplicatesMap.containsKey(fileProp)) {
            List<Path> list = new ArrayList<>();
            list.add(file);
            duplicatesMap.put(fileProp, list);
        } else {
            duplicatesMap.get(fileProp).add(file);
        }
        return FileVisitResult.CONTINUE;
    }
}
