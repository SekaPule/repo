package com.example.repo.training;

import java.util.Locale;

/**
 * Набор тренингов по работе со строками в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see StringsTrainingTest.
 */
public class StringsTraining {

    /**
     * Метод по созданию строки,
     * состоящей из нечетных символов
     * входной строки в том же порядке
     * (нумерация символов идет с нуля)
     *
     * @param text строка для выборки
     * @return новая строка из нечетных
     * элементов строки text
     */
    public String getOddCharacterString(String text) {
        StringBuilder resultText = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {

            if (i % 2 == 0) {
                resultText.append(text.charAt(i));
            }
        }

        return String.valueOf(resultText);
    }

    /**
     * Метод для определения количества
     * символов, идентичных последнему
     * в данной строке
     *
     * @param text строка для выборки
     * @return массив с номерами символов,
     * идентичных последнему. Если таких нет,
     * вернуть пустой массив
     */
    public int[] getArrayLastSymbol(String text) {
        int[] equalCharacterIndexArray;
        char lastCharacter = text.charAt(text.length() - 1);
        int equalCharacterIndexesCount = 0;
        int equalCharacterIndexIterator = 0;

        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i) == lastCharacter) {
                equalCharacterIndexesCount++;
            }
        }

        equalCharacterIndexArray = new int[equalCharacterIndexesCount];

        for (int i = 0; i < text.length() - 1; i++) {
            if (text.charAt(i) == lastCharacter) {
                equalCharacterIndexArray[equalCharacterIndexIterator] = i;
                equalCharacterIndexIterator++;
            }
        }

        return equalCharacterIndexArray;
    }

    /**
     * Метод по получению количества
     * цифр в строке
     *
     * @param text строка для выборки
     * @return количество цифр в строке
     */
    public int getNumbersCount(String text) {
        int numbersCount = 0;

        for (int i = 0; i < text.length() - 1; i++) {
            if ((text.charAt(i) >= '0') && (text.charAt(i) <= '9')) {
                numbersCount++;
            }
        }

        return numbersCount;
    }

    /**
     * Дан текст. Заменить все цифры
     * соответствующими словами.
     *
     * @param text текст для поиска и замены
     * @return текст, где цифры заменены словами
     */
    public String replaceAllNumbers(String text) {
        StringBuilder resultText = new StringBuilder();
        String numberName;

        for (int i = 0; i < text.length(); i++) {
            if ((text.charAt(i) >= '0') && (text.charAt(i) <= '9')) {

                numberName = "";

                if (text.charAt(i) == '0') {
                    numberName = "Ноль";
                }
                if (text.charAt(i) == '1') {
                    numberName = "Один";
                }
                if (text.charAt(i) == '2') {
                    numberName = "Два";
                }
                if (text.charAt(i) == '3') {
                    numberName = "Три";
                }
                if (text.charAt(i) == '4') {
                    numberName = "Четыре";
                }
                if (text.charAt(i) == '5') {
                    numberName = "Пять";
                }
                if (text.charAt(i) == '6') {
                    numberName = "Шесть";
                }
                if (text.charAt(i) == '7') {
                    numberName = "Семь";
                }
                if (text.charAt(i) == '8') {
                    numberName = "Восемь";
                }
                if (text.charAt(i) == '9') {
                    numberName = "Девять";
                }

                resultText.append(numberName);
            } else {
                resultText.append(text.charAt(i));
            }
        }

        return String.valueOf(resultText);
    }

    /**
     * Метод должен заменить заглавные буквы
     * на прописные, а прописные на заглавные
     *
     * @param text строка для изменения
     * @return измененная строка
     */
    public String capitalReverse(String text) {
        StringBuilder resultText = new StringBuilder();
        String changedCharacter;

        for (int i = 0; i < text.length(); i++) {
            if (((text.charAt(i) >= 'a') && (text.charAt(i) <= 'z'))
                    || ((text.charAt(i) >= 'а') && (text.charAt(i) <= 'я'))) {
                changedCharacter = String.valueOf(text.charAt(i)).toUpperCase(Locale.ROOT);
            } else if (((text.charAt(i) >= 'A') && (text.charAt(i) <= 'Z'))
                    || ((text.charAt(i) >= 'А') && (text.charAt(i) <= 'Я'))) {
                changedCharacter = String.valueOf(text.charAt(i)).toLowerCase(Locale.ROOT);
            } else {
                changedCharacter = String.valueOf(text.charAt(i));
            }

            resultText.append(changedCharacter);
        }

        return String.valueOf(resultText);
    }

}
