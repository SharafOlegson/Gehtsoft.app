# Gehtsoft.app
#Console-based Java application "Gehtsoft Arithmetic Evaluator"

## English

### Project Description
**Gehtsoft Arithmetic Evaluator** is a console-based Java application for evaluating arithmetic expressions 
and performing Caesar cipher encryption/decryption, including decryption without specifying the shift value. 
It supports basic operations: addition (+), subtraction (-), multiplication (*), division (/), 
and parentheses for operation precedence.

### How to Compile and Run
1.Install JDK 20 or higher.
2.Compile and run using Maven:
```bash
mvn clean compile
mvn exec:java -Dexec.mainClass="Main"
```
Alternatively, import the Maven project into IntelliJ IDEA and run Main.java.

### Approach and Assumptions
- Expressions are converted into postfix notation using a Shunting Yard algorithm.
- Division by zero throws `ZeroDivideException`.
- Invalid syntax triggers descriptive, custom exceptions.
- Expressions are entered through the console interface.

### Class and Method Overview

#### Main Class
- `Main`: Entry point of the application, manages user interaction.

#### Constants
- `Alphabet`: Defines valid operation characters.

#### Exceptions
- `ZeroDivideException`: Division by zero.
- `ExpressionIsNullOrEmptyException`: Empty expression.
- `NotEnoughNumbersException`: Insufficient numbers (less or more than two).
- `UnbalancedParenthesesException`: Unbalanced parentheses.
- `UnknownOperatorException`: Unknown (invalid) operator.

### Caesar Cipher Module

#### Service
- `CaesarCipherServiceImpl.encryptText(String userText, int shift)`: Encrypts text with a shift (supports both positive and negative shift values).
- `CaesarCipherServiceImpl.decryptText(String userText, int shift)`: Decrypts cipher text back to plain text (with or without a specified shift).

#### Components
- `CaesarCipherComponent.realize(String userText, int shift)`: Major encryption method.
- `CaesarCipherComponent.isLetter(char ch)`: Verifies if a character is a letter in the alphabet.
- `CaesarCipherComponent.getAlphabetForChar(char ch)`: Returns the required alphabet.
- `CaesarCipherComponent.shiftUserChar(char ch, int shift, String alphabet)`: Shifts the character within the alphabet.

### Arithmetic Expression Evaluator Module

#### Service
- `ArithmeticEvaluatorServiceImpl.evaluateExpression(String expression)`: Evaluates a full arithmetic expression.

#### Components
- `ArithmeticEvaluatorComponent.toPostfix(String expression)`: Converts infix to postfix.
- `ArithmeticEvaluatorComponent.calculateReversed(Queue<String> postfix)`: Evaluates postfix expression.
- `ArithmeticEvaluatorComponent.applyOperator(String operator, double b, double a)`: Applies the operator.
- `ArithmeticEvaluatorComponent.getPriority(String operator)`: Returns the priority level of an operator.


### Examples of Usage / Примеры использования 
Сonsole menu:
Welcome to Gehtsoft Technical Assessment
Please choose an option
1. Caesar Cipher Encryption
2. Caesar Cipher Decryption
3. Arithmetic Expression Evaluation
4. Exit

```
Enter your choice: 
3
Enter Arithmetic expression:
(1 + 2) * 8
Result: 24.0

```
Enter your choice: 
1
Choose input method (1 - enter manually, 2 - from file): 
1
Enter text to encrypt: 
Sharaf
Enter shift value: 5
Result: Xmfwfk

```
Enter your choice: 
2
Choose input method (1 - enter manually, 2 - from file): 
1
Enter text to decrypt: 
Xmfwfk
Choose shift method (1- with shift value, 2- without shift value): 
1
Enter shift value: 5
Result: Sharaf

```
## Русский

### Описание проекта
Gehtsoft Arithmetic Evaluator — это консольное Java-приложение для вычисления арифметических выражений и 
Шифрование/дешифрование методом Цезаря, поддерживает обратный сдвиг без указания количества сдвига.
Поддерживаются основных операций: сложение (+), вычитание (-), умножение (*), деление (/) 
и скобок для определения приоритета операций.

### Инструкции по компиляции и запуску
1. Установите JDK 20 или выше.
2. Скомпилируйте и запустите с помощью Maven:

```
mvn clean compile
mvn exec:java -Dexec.mainClass="Main"
```

Или откройте проект в IntelliJ IDEA и запустите `Main.java`.

### Подход и предположения
- Выражения преобразуются в постфиксную запись (алгоритм сортировочной станции).
- Деление на ноль вызывает `ZeroDivideException`.
- Неправильный синтаксис вызывает описательные исключения.
- Ввод выражения осуществляется через консоль.

### Словарь классов и методов

#### Главный класс
- `Main`: Точка входа, обрабатывает ввод пользователя.https://github.com/SharafOlegson/Gehtsoft.app/blob/main/README.md

#### Константы
- `Alphabet`: Определяет допустимые символы операций.

#### Исключения
- `ZeroDivideException`: Деление на ноль.
- `ExpressionIsNullOrEmptyException`: Пустое выражение.
- `NotEnoughNumbersException`: Недостаточно чисел (меньше или больше двух).
- `UnbalancedParenthesesException`: Несбалансированные скобки.
- `UnknownOperatorException`: Неизвестный оператор.


### Модуль Caesar Cipher Implementation

#### Сервис
- `CaesarCipherServiceImpl.encryptText(String userText, int shift)`: Шифрует текст со сдвигом
(поддерживает как положительных, так и отрицательные значения сдвига).
- `CaesarCipherServiceImpl.decryptText(String userText, int shift)`: Дешифровывает зашифрованный
текст обратно в открытый текст (как с получением значения сдвига, так и без него).

#### Компоненты
- `CaesarCipherComponent.realize(String userText, int shift)`: основной метод шифрования.
- `CaesarCipherComponent.isLetter(char ch)`: Подтверждение наличия буквы в алфавите.
- `CaesarCipherComponent.getAlphabetForChar(char ch)`: Возвращает необходимый алфавит.
- `CaesarCipherComponent.shiftUserChar(char ch, int shift, String alphabet)`: Передвигает по алфавиту значение.

### Модуль Arithmetic Expression Evaluator

#### Сервис
- `ArithmeticEvaluatorServiceImpl.evaluateExpression(String expression)`: Обрабатывает выражение.

#### Компоненты
- `ArithmeticEvaluatorComponent.toPostfix(String expression)`: Инфикс в постфикс.
- `ArithmeticEvaluatorComponent.calculateReversed(Queue<String> postfix)`: Вычисление постфикса.
- `ArithmeticEvaluatorComponent.applyOperator(String operator, double b, double a)`: Применяет оператор.
- `ArithmeticEvaluatorComponent.getPriority(String operator)`: Возвращает приоритетную операцию.
