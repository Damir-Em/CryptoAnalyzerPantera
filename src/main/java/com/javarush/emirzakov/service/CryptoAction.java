package com.javarush.emirzakov.service;

import com.javarush.emirzakov.model.Alphabet;

public abstract class CryptoAction {

    protected Alphabet alphabet = new Alphabet();

    protected char shiftChar(char c, int key) {
        int index = alphabet.getCharIndex(c);
        if (index == -1) return c;
        return alphabet.getCharByIndex(index + key);
    }

    public abstract String execute(String text, int key);
}
