package ru.job4j.io.chat;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        System.out.println("Введите фразу");
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        if (str.equalsIgnoreCase(STOP)) {
            System.out.println("жду продолжения");
            while (!scan.nextLine().equalsIgnoreCase(CONTINUE)) {
                System.out.println("Продолжим?");
            }
            run();
        } else if (str.length() == 0) {
            System.out.println("Введите слово-фразу или напишите \"закончить\"");
            run();
        } else if (str.equalsIgnoreCase(CONTINUE)) {
            System.out.println(readPhrases().get(new Random().nextInt(10)));
            run();
        } else if (str.equalsIgnoreCase(OUT)) {
            System.out.println("До встречи!");
        } else {
            System.out.println(readPhrases().get(new Random().nextInt(10)));
            run();
        }
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(botAnswers))) {
            read.lines().map(s -> s + System.lineSeparator()).forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            for (String str : log) {
                pw.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/chatLog.txt", "./data/answers.txt");
        cc.run();
    }
}