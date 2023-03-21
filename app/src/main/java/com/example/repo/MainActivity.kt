package com.example.repo

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.example.repo.data.DataProvider
import com.example.repo.data.db.RepoDatabase
import com.example.repo.data.internet.Api
import com.example.repo.data.internet.retrofit.RetrofitClient
import com.example.repo.data.repository.Repository
import com.example.repo.databinding.ActivityMainBinding
import com.example.repo.model.News
import com.example.repo.ui.screen.*
import com.example.repo.ui.vm.NewsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var checked: Boolean = false
    private val newsViewModel: NewsViewModel by viewModels()
    private val dataProvider = DataProvider(this)
    private val api: Api = RetrofitClient.retrofitService
    private lateinit var repository: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        repository = Repository(
            api = api,
            dataProvider = dataProvider,
            dao = RepoDatabase.configureRoomClient(this).repoDao()
        )
        lifecycleScope.launch(Dispatchers.IO) {
            repository.initDataForCurrentSession()
        }

        val badge = binding.navView.getOrCreateBadge(R.id.navigationNews)
        badge.apply {
            isVisible = true
            backgroundColor = getColor(R.color.macaroni_and_cheese)
        }

        lifecycleScope.launch {
            try {
                newsViewModel.notCheckedNewsCounter.collect { countNotChecked ->
                    badge.number = countNotChecked
                    badge.isVisible = countNotChecked > 0
                }
            } catch (e: Throwable) {
                e.localizedMessage?.let { Log.e("TAG", it) }
            }
        }

        thread {
            val news = dataProvider.getNewsFromAssets() as MutableList<News>
            newsViewModel.setNews(news)
            newsViewModel.setNotCheckedNewsCounter(news.count { !it.isChecked })
        }

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