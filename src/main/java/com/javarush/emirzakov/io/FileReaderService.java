package com.javarush.emirzakov.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileReaderService {

    public String readFromFile(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return "";
        }
    }
}
