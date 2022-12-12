package com.example.repo.training;

/**
 * Набор тренингов по работе с массивами в java.
 * <p>
 * Задания определены в комментариях методов.
 * <p>
 * Проверка может быть осуществлена запуском тестов.
 * <p>
 * Доступна проверка тестированием @see ArraysTrainingTest.
 */
public class ArraysTraining {

    /**
     * Метод должен сортировать входящий массив
     * по возрастранию пузырьковым методом
     *
     * @param valuesArray массив для сортировки
     * @return отсортированный массив
     */
    public int[] sort(int[] valuesArray) {
        final int ARRAY_LENGTH = valuesArray.length;
        int dataBroker; // provides data exchange

        for (int i = 0; i < ARRAY_LENGTH; i++) {
            for (int j = 0; j < ARRAY_LENGTH - i - 1; j++) {
                if (valuesArray[j] > valuesArray[j + 1]) {
                    dataBroker = valuesArray[j];
                    valuesArray[j] = valuesArray[j + 1];
                    valuesArray[j + 1] = dataBroker;
                }
            }
        }

        return valuesArray;
    }

    /**
     * Метод должен возвращать максимальное
     * значение из введенных. Если входящие числа
     * отсутствуют - вернуть 0
     *
     * @param values входящие числа
     * @return максимальное число или 0
     */
    public int maxValue(int... values) {
        int max;

        if (values.length == 0) {
            return 0;
        } else {
            max = values[0];
        }

        for (int value : values) {
            if (max < value) {
                max = value;
            }
        }

        return max;
    }

    /**
     * Переставить элементы массива
     * в обратном порядке
     *
     * @param array массив для преобразования
     * @return входящий массив в обратном порядке
     */
    public int[] reverse(int[] array) {
        int dataBroker;
        int j = array.length - 1; // counter for iteration in reverse order

        for (int i = 0; i < array.length/2; i++) {
            dataBroker = array[i];
            array[i] = array[j];
            array[j] = dataBroker;

            j--;
        }

        return array;
    }

    /**
     * Метод должен вернуть массив,
     * состоящий из чисел Фибоначчи
     *
     * @param numbersCount количество чисел Фибоначчи,
     *                     требуемое в исходящем массиве.
     *                     Если numbersCount < 1, исходный
     *                     массив должен быть пуст.
     * @return массив из чисел Фибоначчи
     */
    public int[] fibonacciNumbers(int numbersCount) {
        int[] fibonacciNumbersArray = new int[numbersCount];
        int prePreviousNumber = 0;

        if (numbersCount < 1) {
            return new int[0];
        }

        fibonacciNumbersArray[0] = 1;

        for (int i = 1; i < numbersCount; i++) {
            fibonacciNumbersArray[i] = fibonacciNumbersArray[i - 1] + prePreviousNumber;
            prePreviousNumber = fibonacciNumbersArray[i - 1];
        }

        return fibonacciNumbersArray;
    }

    /**
     * В данном массиве найти максимальное
     * количество одинаковых элементов.
     *
     * @param array массив для выборки
     * @return количество максимально встречающихся
     * элементов
     */
    public int maxCountSymbol(int[] array) {
        int maxCount = 1;
        int maxCountResult = 0;
        int[] sortedArray = sort(array);

        for (int i = 0; i < sortedArray.length - 1; i++) {
            if (sortedArray[i] == sortedArray[i + 1]) {
                maxCount++;
            } else {
                maxCount = 1;
            }

            if (maxCount > maxCountResult) {
                maxCountResult = maxCount;
            }
        }

        return maxCountResult;
    }
}
