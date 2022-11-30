package ru.job4j.io;

import java.io.*;

public class Analysis {

    public void unavailable(String source, String target) {
        try (BufferedReader read = new BufferedReader(new FileReader(source))) {
            boolean err = false;
            for (String line = read.readLine(); line != null; line = read.readLine()) {
                String[] str = line.split(" ");
                int i = Integer.parseInt(str[0]);
                if (i > 300) {
                    err = true;
                }
                if (err) {
                    try (PrintWriter out = new PrintWriter(
                            new BufferedOutputStream(
                                    new FileOutputStream(target)
                            ))) {
                        out.println(str[1] + ";");

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (PrintWriter out = new PrintWriter(new FileOutputStream("unavailable.csv"))) {
            out.println("15:01:30;15:02:32");
            out.println("15:10:30;23:12:32");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Analysis analis = new Analysis();
        analis.unavailable("./data/source.properties", "./data/target.properties");
    }
}