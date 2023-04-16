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
import com.example.auth_feature.presentation.views.AuthScreenFragment
import com.example.categories_feature.presentation.views.CategoriesScreenFragment
import com.example.repo.databinding.ActivityMainBinding
import com.example.repo.di.app.MainApplication.Companion.appComponent
import com.example.repo.presentation.newslist.view.NewsScreenFragment
import com.example.repo.presentation.newslist.viewmodel.NewsScreenViewModel
import com.example.repo.presentation.profile.views.ProfileScreenFragment
import com.example.search_feature.presentation.views.SearchScreenFragment
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
            loadFragment(CategoriesScreenFragment.newInstance())
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

    companion object {
        private const val CHECKED_FLAG_KEY = "checkedFlagKey"
        private const val AUTH_KEY = "authKey"
        private const val AUTH_BUNDLE_KEY = "authBundleKey"
    }
}