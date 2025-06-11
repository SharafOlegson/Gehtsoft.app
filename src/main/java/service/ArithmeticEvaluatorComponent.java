package service;

import exception.NotEnoughNumbersException;
import exception.UnknownOperatorException;
import exception.UnsupportedOperatorException;
import exception.ZeroDivideException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Stack;

//класс создан для концентрации логики вычислений, гибкости реализации, изолирования от инфраструктурного кода
// и для лёгкости тестирования
public class ArithmeticEvaluatorComponent {
    private final Logger logger = LoggerFactory.getLogger(ArithmeticEvaluatorComponent.class);

    //преобразование в постфиксную запись
    public String toPostfix(String input) {
        StringBuilder result = new StringBuilder();
        Stack<Character> operators = new Stack<>();
        for (int i = 0; i < input.length(); i++) {
            char symbol = input.charAt(i);
            if (Character.isWhitespace(symbol)) {
                continue;
            }
            if (Character.isDigit(symbol) || symbol == '.') {
                String number = extractNumber(input, i);
                result.append(number).append(' ');
                i += number.length() - 1;
            } else if (symbol == '(') {
                operators.push(symbol);
            } else if (symbol == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    result.append(operators.pop()).append(' ');
                }
                operators.pop();
            } else {
                while (!operators.isEmpty() && getPriority(symbol) <= getPriority(operators.peek())) {
                    result.append(operators.pop()).append(' ');
                }
                operators.push(symbol);
            }
        }
        while (!operators.isEmpty()) {
            result.append(operators.pop()).append(' ');
        }
        return result.toString();
    }

    //вычисление постфиксной записи
    public double calculateReversed(String reversed) {
        Stack<Double> numbers = new Stack<>();
        for (int i = 0; i < reversed.length(); i++) {
            char symbol = reversed.charAt(i);
            if (Character.isWhitespace(symbol)) {
                continue;
            }
            if (Character.isDigit(symbol) || symbol == '.') {
                String number = extractNumber(reversed, i);
                numbers.push(Double.parseDouble(number));
                i += number.length() - 1;
            } else {
                applyOperator(symbol, numbers);
            }
        }
        return numbers.pop();
    }

    //метод вычисления приоритета операторов
    private int getPriority(char operator) {
        if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        } else if (operator == '(') {
            return 0;
        } else {
            logger.warn("Unsupported operator: {}", operator);
            throw new UnsupportedOperatorException(operator);
        }
    }

    //извлечение числа
    private String extractNumber(String input, int startIndex) {
        StringBuilder number = new StringBuilder();
        while (startIndex < input.length() &&
                (Character.isDigit(input.charAt(startIndex)) || input.charAt(startIndex) == '.')) {
            number.append(input.charAt(startIndex++));
        }
        return number.toString();
    }

    //применение оператора
    private void applyOperator(char operator, Stack<Double> numbers) {
        if (numbers.size() < 2) {
            logger.warn("Not enough numbers for operator {}", operator);
            throw new NotEnoughNumbersException();//выбрасывает искоючение если чисел меньше 2х
        }
        double b = numbers.pop();
        double a = numbers.pop();
        switch (operator) {
            case '+':
                numbers.push(a + b);
                break;
            case '-':
                numbers.push(a - b);
                break;
            case '*':
                numbers.push(a * b);
                break;
            case '/':
                if (b == 0) {
                    logger.warn("Can't divide by zero!");
                    throw new ZeroDivideException();//выбрасывает искоючение при делении на 0
                }
                numbers.push(a / b);
                break;
            default:
                logger.warn("Unknown operator: {}", operator);
                throw new UnknownOperatorException("Unknown operator: " + operator);//выбрасывает искоючение при неверном операторе
        }
    }
}
