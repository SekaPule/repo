package com.example.repo

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.repo.databinding.ActivityMainBinding
import com.example.repo.di.app.MainApplication.Companion.appComponent
import com.example.repo.presentation.auth.views.AuthScreenFragment
import com.example.repo.presentation.categorieslist.views.CategoriesScreenFragment
import com.example.repo.presentation.newslist.view.NewsScreenFragment
import com.example.repo.presentation.newslist.viewmodel.NewsScreenViewModel
import com.example.repo.presentation.profile.views.ProfileScreenFragment
import com.example.repo.presentation.search.views.SearchScreenFragment
import com.google.android.material.badge.BadgeDrawable
import kotlinx.coroutines.launch
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var checked: Boolean = false

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val newsScreenViewModel: NewsScreenViewModel by viewModels { viewModelFactory }
    private lateinit var badge: BadgeDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        badge = binding.navView.getOrCreateBadge(R.id.navigationNews)

        appComponent().inject(this)
        initDataForCurrentSession()
        setNavigationButtonsListener()
        initBottomBadge()
        initDataContent()
        setBadgeChangeListener()
        setFragmentResultListeners()
        setAuthCheck()
    }

    private fun setAuthCheck() {
        if (!checked) {
            binding.navView.menu.findItem((R.id.navigationHelp)).isChecked = true
            supportFragmentManager.commit {
                replace(
                    binding.screenContainer.id,
                    AuthScreenFragment.newInstance()
                )
            }
        }
    }

    private fun setFragmentResultListeners() {
        supportFragmentManager.setFragmentResultListener(AUTH_KEY, this) { _, bundle ->
            checked = bundle.getBoolean(AUTH_BUNDLE_KEY)
            binding.navView.visibility = View.VISIBLE
        }
    }

    private fun setBadgeChangeListener() {
        lifecycleScope.launch {
            try {
                newsScreenViewModel.notCheckedNewsCounter.collect { countNotChecked ->
                    badge.number = countNotChecked
                    badge.isVisible = countNotChecked > 0
                }
            } catch (e: Throwable) {
                e.localizedMessage?.let { Log.e("TAG", it) }
            }
        }
    }

    private fun initDataContent() {
        newsScreenViewModel.initNews()
    }

    private fun initBottomBadge() {
        badge.apply {
            isVisible = true
            backgroundColor = getColor(R.color.macaroni_and_cheese)
        }
    }

    private fun setNavigationButtonsListener() {
        binding.navView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigationHelp -> {
                    loadFragment(CategoriesScreenFragment.newInstance())
                    checked = true

                    true
                }
                R.id.navigationAccount -> {
                    loadFragment(ProfileScreenFragment.newInstance())
                    checked = true

                    true
                }
                R.id.navigationSearch -> {
                    loadFragment(SearchScreenFragment.newInstance())
                    checked = true

                    true
                }
                R.id.navigationNews -> {
                    loadFragment(NewsScreenFragment.newInstance())
                    checked = true

                    true
                }
                else -> false
            }
        }
    }

    private fun initDataForCurrentSession() {
        newsScreenViewModel.initData()
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