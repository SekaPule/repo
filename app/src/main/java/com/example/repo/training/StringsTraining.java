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

            if (i % 2 != 0) {
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
        if (text.isEmpty()) {
            return new int[0];
        }

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

                switch (text.charAt(i)) {
                    case '0':
                        numberName = "Ноль";
                        break;
                    case '1':
                        numberName = "Один";
                        break;
                    case '2':
                        numberName = "Два";
                        break;
                    case '3':
                        numberName = "Три";
                        break;
                    case '4':
                        numberName = "Четыре";
                        break;
                    case '5':
                        numberName = "Пять";
                        break;
                    case '6':
                        numberName = "Шесть";
                        break;
                    case '7':
                        numberName = "Семь";
                        break;
                    case '8':
                        numberName = "Восемь";
                        break;
                    case '9':
                        numberName = "Девять";
                        break;
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
        Character changedCharacter;

        for (int i = 0; i < text.length(); i++) {
            if (Character.isUpperCase(text.charAt(i))) {
                changedCharacter = Character.toLowerCase(text.charAt(i));
            }else if(Character.isLowerCase(text.charAt(i))){
                changedCharacter = Character.toUpperCase(text.charAt(i));
            }else{
                changedCharacter = text.charAt(i);
            }

            resultText.append(changedCharacter);
        }

        return String.valueOf(resultText);
    }

}
