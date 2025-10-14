package com.javarush.emirzakov.model;
public class Alphabet {

    public static final char[] ALPHABET = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
            'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

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

}