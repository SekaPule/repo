package com.example.repo.training;

/**
 * Набор тренингов по работе с примитивными типами java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see ElementaryTrainingTest.
 */
public class ElementaryTraining {
    final int FIVE_DIGIT_VALUE_UPPER_LIMIT = 99999;
    final int ONE_DIGIT_VALUE_UPPER_LIMIT = 9;

    /**
     * Метод должен возвращать среднее значение
     * для введенных параметров
     *
     * @param firstValue  первый элемент
     * @param secondValue второй элемент
     * @return среднее значение для введенных чисел
     */
    public double averageValue(int firstValue, int secondValue) {
        return ((double) firstValue + (double) secondValue) / 2;
    }

    /**
     * Пользователь вводит три числа.
     * Произвести манипуляции и вернуть сумму новых чисел
     *
     * @param firstValue  увеличить в два раза
     * @param secondValue уменьшить на три
     * @param thirdValue  возвести в квадрат
     * @return сумма новых трех чисел
     */
    public double complicatedAmount(int firstValue, int secondValue, int thirdValue) {
        firstValue *= 2;
        secondValue -= 3;
        thirdValue *= thirdValue;

        return firstValue + secondValue + thirdValue;
    }

    /**
     * Метод должен поменять значение в соответствии с условием.
     * Если значение больше 3, то увеличить
     * на 10, иначе уменьшить на 10.
     *
     * @param value число для изменения
     * @return новое значение
     */
    public int changeValue(int value) {
        if (value > 3) {
            value += 10;
        } else {
            value -= 10;
        }

        return value;
    }

    /**
     * Метод должен менять местами первую
     * и последнюю цифру числа.
     * Обрабатывать максимум пятизначное число.
     * Если число < 10, вернуть
     * то же число
     *
     * @param value число для перестановки
     * @return новое число
     */
    public int swapNumbers(int value) {
        int headDigit = 0;
        int tailDigit = value % 10;
        int valueCopyForProcessing = value;
        int bodyDigit;
        int roundedNumberValue = 1;


        if (value > FIVE_DIGIT_VALUE_UPPER_LIMIT
                || value <= ONE_DIGIT_VALUE_UPPER_LIMIT) {
            return value;
        }

        /*
        *   Cut off the last digit until value becomes one-digit.
        *
        */
        while (valueCopyForProcessing > ONE_DIGIT_VALUE_UPPER_LIMIT) {
            valueCopyForProcessing /= 10;
            tailDigit *= 10;
            roundedNumberValue *= 10;
            headDigit = valueCopyForProcessing;
        }

        bodyDigit = (value / 10 * 10) % roundedNumberValue;

        return bodyDigit + headDigit + tailDigit;
    }

    /**
     * Изменить значение четных цифр числа на ноль.
     * Счет начинать с единицы.
     * Обрабатывать максимум пятизначное число.
     * Если число < 10 вернуть
     * то же число.
     *
     * @param value число для изменения
     * @return новое число
     */
    public int zeroEvenNumber(int value) {
        int valueCopyForProcessing = value;
        int digitsCount = 1;
        int roundedNumberValue = 10;
        int resultValue;
        int remainder;

        if (value > FIVE_DIGIT_VALUE_UPPER_LIMIT
                || value <= ONE_DIGIT_VALUE_UPPER_LIMIT) {
            return value;
        }

        while (valueCopyForProcessing > ONE_DIGIT_VALUE_UPPER_LIMIT) {
            valueCopyForProcessing /= 10;
            roundedNumberValue *= 10;
            digitsCount++;
        }

        resultValue = value;

        for (int i = 1; i <= digitsCount; i++) {
            if (i%2==0){

                /*
                *   Recording the remainder after an odd position. ( 2345 -> 45)
                */
                remainder = resultValue % (roundedNumberValue/10);

                /*
                *   Clear digits from start of odd position. ( 2345 -> 2000 )
                *   Add remainder to result value. (2345 -> 2000 + 45)
                */
                resultValue = ((resultValue / roundedNumberValue) * roundedNumberValue) + remainder;
            }

            roundedNumberValue /= 10;
        }

        return resultValue;
    }
}