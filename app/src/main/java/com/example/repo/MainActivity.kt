package com.example.repo

import android.os.Bundle
import android.os.PersistableBundle
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
    private var checked: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!checked) {
            binding.navView.menu.findItem((R.id.navigationHelp)).isChecked = true
            loadFragment(CategoriesOfHelpingFragment.newInstance())
        }

        binding.navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationHelp -> {
                    loadFragment(CategoriesOfHelpingFragment.newInstance())
                    checked = true

                    true
                }
                R.id.navigationAccount -> {
                    loadFragment(ProfileFragment.newInstance())
                    checked = true

                    true
                }
                R.id.navigationSearch -> {
                    loadFragment(SearchFragment.newInstance())
                    checked = true

                    true
                }
                R.id.navigationNews -> {
                    loadFragment(NewsFragment.newInstance())
                    checked = true

                    true
                }
                else -> false
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putBoolean(CHECKED_FLAG_KEY, checked)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        checked = savedInstanceState.getBoolean(CHECKED_FLAG_KEY, false)
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

    companion object {
        private const val CHECKED_FLAG_KEY = "checkedFlagKey"
    }
}