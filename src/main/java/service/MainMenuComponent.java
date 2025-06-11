package service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class MainMenuComponent {
    private static final Logger logger = LoggerFactory.getLogger(MainMenuComponent.class);

    //проверка ввода числа
    public static int shiftChecker(Scanner scanner) {
        logger.info("Checking shift value");
        int shiftValue;
        while (true) {
            System.out.print("Enter shift value: ");
            String input = scanner.nextLine();
            try {
                shiftValue = Integer.parseInt(input);
                logger.info("Shift value: {}", shiftValue);
                break; // Ввод корректный, выходим из цикла
            } catch (NumberFormatException e) {
                logger.warn(e.getMessage());
                System.out.println("Not correct shift value, please enter correct shift value again: ");
            }
        }
        return shiftValue;
    }
    //показывает главное меню
    public static void showMenu() {
        System.out.println("Please choose an option");
        System.out.println("1. Caesar Cipher Encryption");
        System.out.println("2. Caesar Cipher Decryption");
        System.out.println("3. Arithmetic Expression Evaluation");
        System.out.println("4. Exit");
    }

    public static int getUserChoice(Scanner scanner) {
        logger.info("Getting user choice");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid choice< please enter an integer: ");
            scanner.next();
        }
        int userChoice = scanner.nextInt();
        logger.info("User choice: {}", userChoice);
        scanner.nextLine();
        return userChoice;
    }

    public static boolean askContinue(Scanner scanner) {
        logger.info("Asking to continue app");
        String continueInput;
        while (true) {
            System.out.print("Continue? (y/n): ");
            continueInput = scanner.nextLine().trim().toLowerCase();
            if (continueInput.equals("y") || continueInput.equals("n")) {
                break;
            } else {
                System.out.println("Please enter 'y' or 'n'.");
            }
        }
        logger.info("Users answer to continue app is: {}", continueInput);
        return continueInput.equals("y");
    }

    public static String readTextFromFile(Scanner scanner) {
        logger.info("Starting to read text from file");
        while (true) {
            System.out.println("Enter file path: ");
            String filePath = scanner.nextLine();
            logger.info("FilePath: {}", filePath);
            try {
                return new String(java.nio.file.Files.readAllBytes(java.nio.file.Paths.get(filePath)));
            } catch (java.io.IOException e) {
                logger.info("Error reading from file", e);
                System.out.println("Error reading file: " + e.getMessage());
                System.out.println("Please enter a valid file path again.");
            }
        }
    }
}
