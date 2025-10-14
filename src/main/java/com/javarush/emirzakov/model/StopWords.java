package com.javarush.emirzakov.model;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public final class StopWords {
    public static final Set<String> RUSSIAN = new HashSet<>(Arrays.asList(
            "и", "в", "на", "не", "что", "как", "я", "он", "она", "но", "с",
            "за", "по", "то", "этот", "это", "для", "вы", "мы", "они"
    ));
    public static final Set<String> ENGLISH = new HashSet<>(Arrays.asList(
            "and", "the", "in", "of", "to", "a", "is", "it", "by", "for",
            "on", "that", "this", "with", "as", "you", "I", "we", "he", "she"
    ));
    private StopWords(){

    }
}
