package com.example.repo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.repo.kotlin.*
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

        /* part2 */

        //4.
        val user = User(1, "Ivan", 22, Type.FULL)
        val user1 = User(1, "Ivan", 17, Type.FULL)
        val user2 = User(1, "Ivan", 15, Type.FULL)

        Log.e("ST", user.startTime.toString())
        Thread.sleep(1000)
        Log.e("RepeatST", user.startTime.toString())

        //5.
        val userList = mutableListOf(user)
        userList.apply {
            addAll(
                listOf(
                    User(2, "Ivan2", 22, Type.FULL),
                    User(3, "Ivan3", 22, Type.DEMO),
                    User(4, "Ivan4", 22, Type.FULL),
                    User(5, "Ivan5", 22, Type.DEMO)
                )
            )

        }

        //6.
        val fullAccessUserList = userList.filter { it.type == Type.FULL }

        //7.
        val userNameList = userList.map {
            it.name
        }

        Log.e("FIRST", userNameList.first())
        Log.e("LAST", userNameList.last())

        //9.
        val authCallback = object : AuthCallback {
            override fun authSuccess() {
                Log.e("AUTH", "Success")
            }

            override fun authFailed() {
                Log.e("AUTH", "Failed")
            }
        }

        //13.
        user.doAction(Registration(), authCallback)
        user.doAction(Login(user), authCallback)
        user1.doAction(Login(user1), authCallback)
        user2.doAction(Logout(), authCallback)
    }
}