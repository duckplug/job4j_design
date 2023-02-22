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
        List<String> phrases = readPhrases();
        List<String> logs = new ArrayList<>();
        System.out.println("Введите фразу");
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        logs.add(str);
        boolean checkStop;
        while (!OUT.equalsIgnoreCase(str)) {
            if (STOP.equalsIgnoreCase(str)) {
                checkStop = true;
                while (checkStop) {
                    str = scan.nextLine();
                    logs.add(str);
                    if (CONTINUE.equalsIgnoreCase(str) || OUT.equalsIgnoreCase(str)) {
                        checkStop = false;
                    }
                }
            }
            if (!OUT.equalsIgnoreCase(str)) {
                str = phrases.get(new Random().nextInt(phrases.size()));
                System.out.println(str);
                logs.add(str);
                str = scan.nextLine();
                logs.add(str);
            }
        }
        saveLog(logs);
    }

    private List<String> readPhrases() {
        List<String> list = new ArrayList<>();
        try (BufferedReader read = new BufferedReader(new FileReader(botAnswers))) {
            read.lines().forEach(list::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, true))) {
            for (String str : log) {
                pw.println(str);
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