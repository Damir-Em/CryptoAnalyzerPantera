package com.javarush.emirzakov.service;

public class EncryptAction extends CryptoAction {

    @Override
    public String execute(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            result.append(shiftChar(ch, key));
        }
        return result.toString();
    }
}
