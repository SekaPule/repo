package com.example.repo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.repo.databinding.ActivityMainBinding
import com.example.repo.ui.screen.CategoriesOfHelpingFragment
import com.example.repo.ui.screen.NewsFragment
import com.example.repo.ui.screen.ProfileFragment
import com.example.repo.ui.screen.SearchFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navView.menu.findItem((R.id.navigationHelp)).isChecked = true
        loadFragment(CategoriesOfHelpingFragment.newInstance())

        binding.navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationHelp -> {
                    loadFragment(CategoriesOfHelpingFragment.newInstance())

                    true
                }
                R.id.navigationAccount -> {
                    loadFragment(ProfileFragment.newInstance())

                    true
                }
                R.id.navigationSearch -> {
                    loadFragment(SearchFragment.newInstance())

                    true
                }
                R.id.navigationNews -> {
                    loadFragment(NewsFragment.newInstance())

                    true
                }
                else -> false
            }
        }

//        binding.navView.isGone = true
    }

    private fun loadFragment(fragment: Fragment) {
        // load fragment
        supportFragmentManager.commit {
            replace(
                R.id.screenContainer,
                fragment
            )
        }
    }
}