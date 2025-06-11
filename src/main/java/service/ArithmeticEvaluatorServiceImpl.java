package service;

import exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//Использовано Reverse Polish Notation
//Код частично взят с сайта https://habr.com/ru/articles/596925/
public class ArithmeticEvaluatorServiceImpl implements ArithmeticEvaluatorService{
    private final Logger logger = LoggerFactory.getLogger(ArithmeticEvaluatorServiceImpl.class);
    private final ArithmeticEvaluatorComponent arithmeticEvaluatorComponent;
    public ArithmeticEvaluatorServiceImpl(ArithmeticEvaluatorComponent arithmeticEvaluatorComponent) {
        this.arithmeticEvaluatorComponent = arithmeticEvaluatorComponent;
    }

    //главный метод, который принимает строку выражения и вычисляет ёё выдавая результат
    @Override
    public double evaluateExpression(String expression) {
        logger.debug("Starting to evaluate expression");
        validateMathExpressionInput(expression); //Проверка на null и отсутствия выражения
        checkBalancedParentheses(expression);//Проверка сбалансированности скобок
        String reversedNotation = arithmeticEvaluatorComponent.toPostfix(expression);//перевод в постфиксную форму
        logger.debug("Converted to postfix notation: '{}'", reversedNotation);
        double result = arithmeticEvaluatorComponent.calculateReversed(reversedNotation);//вычисление выражения
        logger.debug("Successfully evaluated expression. Result is : {}", result);
        return result;
    }

    //Проверка на null и отсутствия выражения
    private void validateMathExpressionInput(String expression) {
        logger.debug("Validating expression");
        if (expression == null || expression.trim().isEmpty()) {
            logger.warn("Input expression is null or empty");
            throw new ExpressionIsNullOrEmptyException();
        }
        logger.debug("Successfully validated expression");
    }

    //Провка сбалансированности скобок
    private void checkBalancedParentheses(String input) {
        logger.debug("Checking parentheses balance");
        int balance = 0;
        for (char ch : input.toCharArray()) {
            if (ch == '(') {
                balance++;
            } else if (ch == ')') {
                balance--;
                if (balance < 0) {// Закрывающая без открывающей
                    logger.warn("Unbalanced parentheses in expression: '{}'",input);
                    throw new UnbalancedParenthesesException(input);
                }
            }
        }
        if (balance > 0) {
            logger.warn("Unbalanced parentheses in expression: '{}'",input);
            throw new UnbalancedParenthesesException(input);
        }
        logger.debug("Successfully checked parentheses balance");
    }
}
