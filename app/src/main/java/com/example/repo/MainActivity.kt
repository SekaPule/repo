package com.example.repo

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.repo.data.DataProvider
import com.example.repo.databinding.ActivityMainBinding
import com.example.repo.model.News
import com.example.repo.ui.screen.*
import com.example.repo.ui.vm.NewsViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var checked: Boolean = false
    private val newsViewModel: NewsViewModel by viewModels()
    private val dataProvider = DataProvider(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val badge = binding.navView.getOrCreateBadge(R.id.navigationNews)
        badge.apply {
            isVisible = true
            backgroundColor = getColor(R.color.macaroni_and_cheese)
        }

        newsViewModel.news?.subscribe(
            { news ->
                val countNotChecked = news.count { !it.isChecked }
                badge.number = countNotChecked
                badge.isVisible = countNotChecked > 0
            },
            { error ->
                error.localizedMessage?.let { Log.e("TAG", it) }
            }
        )

        newsViewModel.setNews(dataProvider.getNewsFromAssets() as MutableList<News>)

        supportFragmentManager.setFragmentResultListener(AUTH_KEY, this) { _, bundle ->
            checked = bundle.getBoolean(AUTH_BUNDLE_KEY)
            binding.navView.visibility = View.VISIBLE
        }

        if (!checked) {
            binding.navView.menu.findItem((R.id.navigationHelp)).isChecked = true
            supportFragmentManager.commit {
                replace(
                    binding.screenContainer.id,
                    AuthFragment.newInstance()
                )
            }
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
        private const val AUTH_KEY = "authKey"
        private const val AUTH_BUNDLE_KEY = "authBundleKey"
    }
}