package com.javarush.emirzakov.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileWriterService {
    public void writeFile(String path, CharSequence content) {
        try {
            Files.writeString(Path.of(path), content);
            System.out.println("Files successfully written to: " + path);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
