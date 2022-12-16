package com.example.repo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.repo.kotlin.KotlinPart1
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val kotlinPart1 = KotlinPart1()

        //3.
        val book3 = KotlinPart1.Book(BigDecimal(15.5), 7500)
        val book4 = KotlinPart1.Book(BigDecimal(5.9), 1000)
        val magazine = KotlinPart1.Magazine(BigDecimal(1.56), 300)

        kotlinPart1.printInfo(book3)
        kotlinPart1.printInfo(book4)
        kotlinPart1.printInfo(magazine)

        kotlinPart1.printCompareResults(book3, book4)

        //4.
        val book1: KotlinPart1.Book? = null
        val book2 = KotlinPart1.Book(BigDecimal(56.9), 1000)

        book1?.let {
            kotlinPart1.buy(it)
        }
        book2.let {
            kotlinPart1.buy(it)
        }

        //5.
        kotlinPart1.sum.invoke(13, 6)
    }
}