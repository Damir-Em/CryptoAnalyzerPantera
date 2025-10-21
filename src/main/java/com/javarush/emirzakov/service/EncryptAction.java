package com.javarush.emirzakov.service;

import com.javarush.emirzakov.model.Alphabet;

public class EncryptAction extends CryptoAction {

    @Override
    public String execute(String text, int key) {
        Alphabet alphabet = new Alphabet(text);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            result.append(shiftChar(ch, key, alphabet));
        }
        return result.toString();
    }
}
