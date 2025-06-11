import exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.*;

import java.util.Scanner;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) {

        CaesarCipherComponent caesarCipherComponent = new CaesarCipherComponent();
        CaesarCipherService caesarCipherService = new CaesarCipherServiceImpl(caesarCipherComponent);
        ArithmeticEvaluatorComponent evaluatorComponent = new ArithmeticEvaluatorComponent();
        ArithmeticEvaluatorServiceImpl arithmeticEvaluatorServiceImpl = new ArithmeticEvaluatorServiceImpl(evaluatorComponent);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Gehtsoft Technical Assessment");
        boolean appRun = true;

        while (appRun) {
            logger.info("application started");
            int userChoice;
            while (true) {
                MainMenuComponent.showMenu();
                logger.info("showing main menu");
                System.out.println("Enter your choice: ");
                userChoice = MainMenuComponent.getUserChoice(scanner);
                logger.info("User chose choice: {}", userChoice);
                if (userChoice >= 1 && userChoice <= 4) {
                    break; // корректный выбор
                } else {
                    logger.info("user chose invalid choice");
                    System.out.println("Invalid choice, please try again.");
                }
            }
            switch (userChoice) {
                case 1:
                    logger.info("user chose case encrypt");
                    handleEncrypt(scanner, caesarCipherService);
                    break;
                case 2:
                    logger.info("user chose case decrypt");
                    handleDecrypt(scanner, caesarCipherService);
                    break;
                case 3:
                    logger.info("user chose case arithmetic evaluator");
                    handleArithmetic(scanner, arithmeticEvaluatorServiceImpl);
                    break;
                case 4:
                    logger.info("user finish app");
                    appRun = false;
                    break;
            }
            if (appRun) {
                appRun = MainMenuComponent.askContinue(scanner);
            }
        }
        logger.info("Application is shutting down");
        System.out.println("Good Bye");
    }

    private static void handleEncrypt(Scanner scanner, CaesarCipherService caesarCipherService) {
        logger.info("User selected encryption");
        System.out.println("Choose input method (1 - enter manually, 2 - from file): ");
        int inputMethodEncrypt = Integer.parseInt(scanner.nextLine().trim());
        String encryptText;
        if (inputMethodEncrypt == 2) {
            logger.info("User chose to input text from file for encrypt");
            encryptText = MainMenuComponent.readTextFromFile(scanner);
        } else {
            logger.info("User entered text manually");
            System.out.println("Enter text to encrypt: ");
            encryptText = scanner.nextLine();
        }
        int encryptShiftValue = MainMenuComponent.shiftChecker(scanner);
        String encryptResult = caesarCipherService.encryptText(encryptText, encryptShiftValue);
        System.out.println("Result: " + encryptResult);
        logger.info("Encrypt result: {}", encryptResult);
    }

    private static void handleDecrypt(Scanner scanner, CaesarCipherService caesarCipherService) {
        logger.info("User selected decryption");
        System.out.println("Choose input method (1 - enter manually, 2 - from file): ");
        int inputMethodDecrypt = Integer.parseInt(scanner.nextLine().trim());
        String decryptText;
        if (inputMethodDecrypt == 2) {
            logger.info("User chose to input text from file for decrypt");
            decryptText = MainMenuComponent.readTextFromFile(scanner);
        } else {
            logger.info("User entered text manually");
            System.out.println("Enter text to decrypt: ");
            decryptText = scanner.nextLine();
        }
        boolean validChoice = false;
        while (!validChoice) {
            System.out.println("Choose shift method (1- with shift value, 2- without shift value): ");
            int inputShiftMethod = Integer.parseInt(scanner.nextLine().trim());
            if (inputShiftMethod == 1) {
                logger.info("User chose shift method with shift value");
                int decryptShiftValue = MainMenuComponent.shiftChecker(scanner);
                String decryptResult = caesarCipherService.decryptText(decryptText, decryptShiftValue);
                System.out.println("Result: " + decryptResult);
                logger.info("Decrypt result: {}", decryptResult);
                validChoice = true;
            } else if (inputShiftMethod == 2) {
                logger.info("User chose shift method without shift value");
                boolean isRussian = decryptText.matches(".*[А-Яа-я].*"); // Проверка на русский алфавит
                int alphabetSize = isRussian ? 33 : 26;
                for (int shift = 1; shift < alphabetSize; shift++) {
                    String decryptResult = caesarCipherService.decryptText(decryptText, -shift);
                    System.out.println("Shift " + shift + ": " + decryptResult);
                }
                validChoice = true;
            } else {
                System.out.println("You choose incorrect number of shift method, please try again: ");
            }
        }
    }

    private static void handleArithmetic(Scanner scanner, ArithmeticEvaluatorServiceImpl arithmeticEvaluatorServiceImpl) {
        logger.info("Stating arithmetic evaluator");
        while (true) {
            System.out.println("Enter Arithmetic expression: ");
            String expression = scanner.nextLine();
            try {
                double expressionResult = arithmeticEvaluatorServiceImpl.evaluateExpression(expression);
                System.out.println("Result: " + expressionResult);
                logger.info("Expression result is: {}", expressionResult);
                break;
            } catch (ExpressionIsNullOrEmptyException |
                     UnbalancedParenthesesException |
                     NotEnoughNumbersException |
                     ZeroDivideException |
                     UnknownOperatorException |
                     UnsupportedOperatorException e) {
                logger.warn("Error while evaluating expression", e);
                System.out.println(e.getMessage());
            }
        }
    }
}

