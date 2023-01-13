package ru.job4j.io;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExSample {
    public static void main(String[] args) {
        Pattern patternEng = Pattern.compile("WORD", Pattern.CASE_INSENSITIVE);
        String wordEng = "word";
        Matcher matcherEng = patternEng.matcher(wordEng);

        Pattern patternRu = Pattern.compile("СЛОВО", Pattern.CASE_INSENSITIVE);
        String wordRu = "слово";
        Matcher matcherRu = patternRu.matcher(wordRu);


        System.out.println(matcherEng.matches());
        System.out.println(matcherRu.matches());
    }
}
