package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DuplicatesFinder {
    private void  showDuplicates() throws IOException {
        DuplicatesVisitor dv = new DuplicatesVisitor();
        Files.walkFileTree(Path.of("./"), dv);
        for (String str:dv.getDuplicates().keySet()) {
            System.out.printf("%s - %s \n", dv.getDuplicates().get(str).getName(), str);
        }
    }

    public static void main(String[] args) throws IOException {
        DuplicatesFinder df = new DuplicatesFinder();
        df.showDuplicates();
    }
}