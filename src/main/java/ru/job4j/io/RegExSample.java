package ru.job4j.io;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExSample {
        public static void main(String[] args) {
            Pattern pattern = Pattern.compile("\\S+@\\S+\\.\\S+");
            String text = "peter-2022@example.com example65@mail_box.ru 123_45@example-mailbox.com";
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                System.out.println("Найдено совпадение: " + matcher.group());
            }
        }
    }