package com.example.repo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.repo.databinding.ActivitySplashScreenBinding
import java.util.*
import kotlin.concurrent.timerTask


@SuppressLint("CustomSplashScreen")
class ActivitySplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timer().schedule(timerTask {
            val intent = Intent(this@ActivitySplashScreen, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }
}