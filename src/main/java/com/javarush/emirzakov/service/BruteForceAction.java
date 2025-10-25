package com.javarush.emirzakov.service;

import com.javarush.emirzakov.model.Alphabet;
import com.javarush.emirzakov.model.StopWords;

import java.util.List;
import java.util.Set;

public class BruteForceAction {

    public static class Result {
        public final int key;
        public final String text;
        public final int score;

        public Result(int key, String text, int score) {
            this.key = key;
            this.text = text;
            this.score = score;
        }
    }

    public Result findBestByStopWords(String cipherText) {
        if (cipherText == null || cipherText.isBlank()) return null;

        DecryptAction decryptAction = new DecryptAction();
        int N = new Alphabet(cipherText).length();

        int bestScore = -1;
        Result best = null;

        for (int k = 0; k < N; k++) {
            String candidate = decryptAction.execute(cipherText, k);
            String sample = shortenForAnalysis(candidate);
            String norm = TextUtils.normalize(sample);
            List<String> tokens = TextUtils.tokenize(norm);

            int rusMatches = countStopWords(tokens, StopWords.RUSSIAN);
            int engMatches = countStopWords(tokens, StopWords.ENGLISH);
            int matches = Math.max(rusMatches, engMatches);

            if (matches > bestScore) {
                bestScore = matches;
                best = new Result(k, candidate, matches);
            }
        }
        return best;
    }

    private String shortenForAnalysis(String text) {
        int maxLen = 1000;
        if (text == null) return "";
        if (text.length() <= maxLen) return text;

        int cutIndex = text.lastIndexOf(' ', maxLen);
        if (cutIndex == -1) {
            cutIndex = maxLen;
        }
        return text.substring(0, cutIndex).trim();
    }

    private int countStopWords(List<String> tokens, Set<String> stopSet) {
        if (tokens == null || tokens.isEmpty() || stopSet == null || stopSet.isEmpty()) return 0;
        int count = 0;
        for (String t : tokens) {
            if (stopSet.contains(t)) count++;
        }
        return count;
    }

}
