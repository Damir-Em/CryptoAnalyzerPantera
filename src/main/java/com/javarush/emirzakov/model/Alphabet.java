package com.javarush.emirzakov.model;

public class Alphabet {
    private final char[] ALPHABET;

    private static final char[] ENGLISH = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    private static final char[] RUSSIAN = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя".toCharArray();


    public Alphabet(String text) {
        if (text.matches(".*[а-яА-ЯёЁ].*")) {
            ALPHABET = RUSSIAN;
        } else {
            ALPHABET = ENGLISH;
        }
    }

    public int getCharIndex(char c) {
        for (int i = 0; i < ALPHABET.length; i++) {
            if (Character.toLowerCase(ALPHABET[i]) == Character.toLowerCase(c)) {
                return i;
            }
        }
        return -1;
    }

    public char getCharByIndex(int index) {
        int i = index % ALPHABET.length;
        if (i < 0) i += ALPHABET.length;
        return ALPHABET[i];
    }

    public int length() {
        return ALPHABET.length;
    }

}