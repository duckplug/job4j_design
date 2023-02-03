package ru.job4j.io;


import java.io.File;
import java.io.IOException;

public class FileExample {
    public static void main(String[] args) throws IOException {
        File file = new File("file.txt");
        file.createNewFile();
     }
}