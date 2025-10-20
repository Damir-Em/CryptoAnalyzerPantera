package com.javarush.emirzakov.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtils {
    private static final Pattern WORD_PATTERN = Pattern.compile("\\b[а-яёА-ЯЁa-zA-Z']+\\b");

    public static String normalize(String text) {
        if (text == null) return "";
        return text.replaceAll("\\s+", " ").trim();
    }

    public static List<String> tokenize(String normalizedText) {
        List<String> tokens = new ArrayList<>();
        if (normalizedText == null || normalizedText.isEmpty()) return tokens;

        Matcher matcher = WORD_PATTERN.matcher(normalizedText);
        while (matcher.find()) {
            tokens.add(matcher.group());
        }
        return tokens;
    }
}
