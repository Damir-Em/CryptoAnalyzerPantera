package com.javarush.emirzakov.main.java;


import com.javarush.emirzakov.io.FileReaderService;
import com.javarush.emirzakov.io.FileWriterService;
import com.javarush.emirzakov.service.BruteForceAction;
import com.javarush.emirzakov.service.DecryptAction;
import com.javarush.emirzakov.service.EncryptAction;
import com.javarush.emirzakov.service.TextUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
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
            String normalized = TextUtils.normalize(text);
            List<String> tokens = TextUtils.tokenize(normalized);

            System.out.println("Normalized text: ");
            System.out.println(normalized.substring(0, Math.min(300, normalized.length())) + "...");
            //System.out.println("Tokens: " + tokens);


            System.out.println("\nChoose action: ");
            System.out.println("1 - Encrypt");
            System.out.println("2 - Decrypt");
            System.out.println("3 - Brute Force (try to find key automatically)");

            int choice = readInt(scanner, "Enter choice (1-3): ");
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
                case 3 -> {
                    BruteForceAction bf = new BruteForceAction();
                    BruteForceAction.Result best = bf.findBestByStopWords(text);

                    if (best != null) {
                        System.out.println("\n Brute-force result:");
                        System.out.println("Key: " + best.key + " Score: " + best.score);
                        System.out.println("Candidate text:\n" + best.text);
                        result = best.text;
                    } else {
                        System.out.println("No likely candidate found by brute force.");
                    }
                }
                default -> System.out.println("Unknown action. Exiting.");
            }
            if (result != null) {
                System.out.println("\n=== Result ===");
                System.out.println(result);

                System.out.println("\nDo you want to save the result to a file? (y/n)");
                String answer = scanner.nextLine().trim().toLowerCase();
                if (answer.equals("y")) {
                    System.out.println("Enter path to file: ");
                    String filePath = scanner.nextLine().trim();

                    FileWriterService fileWriter = new FileWriterService();
                    fileWriter.writeFile(filePath, result);
                    //System.out.println("Result saved to " + filePath);
                }
            }
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.println(prompt);
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                System.out.println("Please enter 1, 2, or 3, then press Enter.");
                continue;
            }
            if (!line.matches("\\d+")) {
                System.out.println("Please enter a valid integer.");
                continue;
            }
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Number too large. Try again.");
            }
        }
    }
}