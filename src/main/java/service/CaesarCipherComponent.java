package service;

import constants.Alphabet;
//класс был создан для отделения технических методов от основных, с фокусировкой на реализации,
// инкапсуляции низкоуровневой логики и для изолированности и лёгкости в тестировании
public class CaesarCipherComponent {
    //основной метод
    public String realize(String userText, int shift) {
        StringBuilder result = new StringBuilder();
        for (char ch : userText.toCharArray()) {
            if (!isLetter(ch)) {
                result.append(ch); // оставляем пробелы и другие символы как есть
                continue;
            }
            String alphabet = getAlphabetForChar(ch);
            result.append(shiftUserChar(ch, shift, alphabet));
        }
        return result.toString();
    }
    //подтверждение наличия в алфавите
    private boolean isLetter(char ch) {
        return Alphabet.RUSSIAN_UPPER.indexOf(ch) != -1 ||
                Alphabet.RUSSIAN_LOWER.indexOf(ch) != -1 ||
                Alphabet.ENGLISH_UPPER.indexOf(ch) != -1 ||
                Alphabet.ENGLISH_LOWER.indexOf(ch) != -1;
    }
    //возвращает необходимый алфавит
    public String getAlphabetForChar(char ch) {
        if (Alphabet.RUSSIAN_UPPER.indexOf(ch) != -1) {
            return Alphabet.RUSSIAN_UPPER;
        } else if (Alphabet.RUSSIAN_LOWER.indexOf(ch) != -1) {
            return Alphabet.RUSSIAN_LOWER;
        } else if (Alphabet.ENGLISH_UPPER.indexOf(ch) != -1) {
            return Alphabet.ENGLISH_UPPER;
        } else {
            return Alphabet.ENGLISH_LOWER;
        }
    }
    //передвигает по алфавиту значение
    public char shiftUserChar(char ch, int shift, String alphabet) {
        int oldPosition = alphabet.indexOf(ch);
        //Используется формула: (letter + shift(or key)) % alphabet.length()
        int newPosition = (oldPosition + shift) % alphabet.length();
        if (newPosition < 0) {
            newPosition += alphabet.length();
        }
        return alphabet.charAt(newPosition);
    }
}

