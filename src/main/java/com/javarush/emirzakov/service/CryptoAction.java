package com.javarush.emirzakov.service;

import com.javarush.emirzakov.model.Alphabet;

public abstract class CryptoAction {

    protected Alphabet alphabet = new Alphabet();

    protected char shiftChar(char c, int key) {
        if (!Character.isLetter(c)) return c;
        boolean isUpper = Character.isUpperCase(c);

        char lower = Character.toLowerCase(c);
        int index = alphabet.getCharIndex(lower);
        if (index == -1) {
            return c;
        }
        char shifted = alphabet.getCharByIndex(index + key);
        if (isUpper) return Character.toUpperCase(shifted);
        return shifted;
    }

    public abstract String execute(String text, int key);
}
