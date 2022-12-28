package com.example.repo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.repo.databinding.ActivityMainBinding
import com.example.repo.ui.screen.ProfileFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navView.menu.findItem((R.id.navigationAccount)).isChecked = true
        supportFragmentManager.commit {
            replace(
                R.id.screenContainer,
                ProfileFragment.newInstance()
            )
            setReorderingAllowed(true)
        }
    }
}