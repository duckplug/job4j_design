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
        Set<FileProperty> set =  dv.duplicatesMap.keySet();
        for (FileProperty fp: set) {
            if (dv.duplicatesMap.get(fp).size() > 1) {
                System.out.println(fp.getName() + ":");
                for (Path path:dv.duplicatesMap.get(fp)) {
                    System.out.println(path);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        DuplicatesFinder df = new DuplicatesFinder();
        df.showDuplicates();
    }
}