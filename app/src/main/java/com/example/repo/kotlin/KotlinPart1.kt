package com.example.repo.kotlin

import android.util.Log
import java.math.BigDecimal
import java.math.RoundingMode

class KotlinPart1 {

    /*
      2.

      Необходимо создать интерфейс Publication, у которого должно быть свойства – price и
      wordCount, а также метод getType, возвращающий строку. Создать два класса, реализующих данный
      интерфейс – Book и Magazine. В методе getType для класса Book возвращаем строку с зависимости
      от количества слов. Если количество слов не превышает 1000, то вывести “Flash Fiction”, 7,500
      –“Short Story”, всё, что выше – “Novel”. Для класса Magazine возвращаем строку “Magazine”.
     */
    interface Publication {
        val price: BigDecimal?
        val wordCount: Int?

        fun getType(): String
    }


    class Book(override val price: BigDecimal?, override val wordCount: Int?) : Publication {
        override fun getType(): String {
            wordCount?.let {
                return if (it <= 1000) {
                    "Flash Fiction"
                } else if (it <= 7500) {
                    "Short Story"
                } else {
                    "Novel"
                }
            } ?: return "Null"
        }

        override fun equals(other: Any?): Boolean = (other is Book)
                && this.price == other.price
                && this.wordCount == other.wordCount
    }


    class Magazine(override val price: BigDecimal?, override val wordCount: Int?) : Publication {
        override fun getType(): String {
            return "Magazine"
        }

    }

    /*
      3.

      Создать два объекта класса Book и один объект Magazine. Вывести в лог для каждого объекта тип,
      количество строк и цену в евро в отформатированном виде. У класса Book переопределить метод
      equals и произвести сравнение сначала по ссылке, затем используя метод equals. Результаты
      сравнений вывести в лог.
     */
    private fun toCurrencyFormat(price: BigDecimal?): String =
        "${price?.setScale(2, RoundingMode.DOWN)}€"

    fun printInfo(publication: Publication) {
        Log.e(
            "INFO", "\nType: ${publication.getType()}" +
                    "\nWord Count: ${publication.wordCount}" +
                    "\nPrice: ${toCurrencyFormat(publication.price)}"
        )
    }

    fun printCompareResults(book1: Book, book2: Book) {
        Log.e(
            "COMPARING", "\nComparison by link: ${book1 === book2}" +
                    "\nComparison by equals: ${book1 == book2}"
        )
    }

    /*
      4.

      Создать метод buy, который в качестве параметра принимает Publication (notnull - значения)
      и выводит в лог “The purchase is complete. The purchase amount was [цена издания]”. Создать
      две переменных класса Book, в которых могут находиться null значения. Присвоить одной null,
      а второй любое notnull значение. Используя функцию let, попробуйте вызвать метод buy с каждой
      из переменных.
     */
    fun buy(publication: Publication) {
        Log.e(
            "BUY", "The purchase is complete. The purchase amount was " +
                    toCurrencyFormat(publication.price)
        )
    }

    /*
      5.

      Создать переменную sum и присвоить ей лямбда-выражение, которое будет складывать два
      переданных ей числа и выводить результат в лог. Вызвать данное лямбда-выражение с
      произвольными параметрами.
     */
    val sum = { firstNum: Int, secondNum: Int ->
        Log.e("SUM", "${firstNum + secondNum}")
    }
}