package com.javarush.emirzakov.main.java;


import com.javarush.emirzakov.service.DecryptAction;
import com.javarush.emirzakov.service.EncryptAction;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {

        FileReaderService fileReader = new FileReaderService();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter text directly, or specify a file path: ");
            String input = scanner.nextLine();

            String text;
            Path path = Path.of(input);
            if (Files.exists(path) && Files.isRegularFile(path)) {
                System.out.println("File has been found and read!");
                text = fileReader.readFromFile(input);
            } else {
                text = input;
                System.out.println("Using input text directly");
            }
            System.out.println("\nChoose action: ");
            System.out.println("1 - Encrypt");
            System.out.println("2 - Decrypt");

            int choice = readInt(scanner, "Enter choice: ");
            String message = "Enter key (integer): ";
            String result = null;

            switch (choice) {
                case 1 -> {
                    int key = readInt(scanner, message);
                    EncryptAction encryptAction = new EncryptAction();
                    result = encryptAction.execute(text, key);
                }
                case 2 -> {
                    int key = readInt(scanner, message);
                    DecryptAction decryptAction = new DecryptAction();
                    result = decryptAction.execute(text, key);
                }
                default -> System.out.println("Unknown action. Exiting.");
            }
            if (result != null) {
                System.out.println("Result:");
                System.out.println(result);

                System.out.println("\nDo you want to save the result to a file? (y/n)");
                String answer = scanner.nextLine().trim().toLowerCase();
                if (answer.equals("y")) {
                    System.out.println("Enter path to file: ");
                    String filePath = scanner.nextLine().trim();

                    FileWriterService fileWriter = new FileWriterService();
                    fileWriter.writeFile(filePath, result);
                }
            }
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter valid integer.");
            }
        }
    }
}