package com.example.repo.ui.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repo.R
import com.example.repo.data.DataProvider
import com.example.repo.data.internet.retrofit.RetrofitClient
import com.example.repo.data.repository.Repository
import com.example.repo.databinding.FragmentNewsBinding
import com.example.repo.model.News
import com.example.repo.recycler.adapter.NewsAdapter
import com.example.repo.recycler.utils.NewsMarginItemDecoration
import com.example.repo.ui.vm.NewsViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.Disposable


class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private lateinit var dataProvider: DataProvider
    private val api = RetrofitClient.configureRetrofit()
    private lateinit var repository: Repository
    private val newsAdapter by lazy { NewsAdapter() }
    private var newsList: List<News>? = null
    private val newsViewModel: NewsViewModel by activityViewModels()
    private lateinit var disposable: Disposable

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataProvider = DataProvider(requireContext())
        repository = Repository(api = api, dataProvider = dataProvider)
        binding = FragmentNewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.newsRV.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
            addItemDecoration(
                NewsMarginItemDecoration(
                    resources.getDimensionPixelSize(R.dimen.spacing_xs)
                )
            )
        }

        if (newsList == null || newsList!!.isEmpty()) {
            binding.progressBar.visibility = View.VISIBLE

            disposable = repository.getNews()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ news ->
                binding.progressBar.visibility = View.GONE
                newsList = news
                newsViewModel.setNews(news)
                newsViewModel.setNotCheckedNewsCounter(news.count { !it.isChecked })
                newsAdapter.submitList(news)
            }, { error ->
                Log.e("TAG", "$error")
            })
        }

        binding.toolBar.title = resources.getString(R.string.news_title)
        binding.toolBar.setOnMenuItemClickListener {
            parentFragmentManager.commit {
                replace(
                    R.id.screenContainer,
                    FilterFragment.newInstance()
                )
                addToBackStack("")
            }


            true
        }

        parentFragmentManager.setFragmentResultListener(
            FILTER_RESULT_KEY,
            requireActivity()
        ) { _, bundle ->
            val filters: List<String> = bundle.getParcelableArrayList(FILTER_BUNDLE_KEY)!!

            if (filters.isNotEmpty()) {
                val newsFiltered: List<News> = newsAdapter.currentList.filter { news ->
                    filters.any { filter ->
                        news.category!!.contains(filter)
                    }
                }

                newsList = newsFiltered as MutableList<News>
                newsViewModel.setNews(newsList!!)
                newsViewModel.setNotCheckedNewsCounter(newsList!!.count { !it.isChecked })
            }
        }
    }

    override fun onResume() {
        super.onResume()

        newsList?.let { newsViewModel.setNews(it) }
        newsList?.let { list ->
            newsViewModel.setNotCheckedNewsCounter(list.count { !it.isChecked })
        }
        newsAdapter.submitList(newsList)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposable.dispose()
    }

    companion object {
        private const val FILTER_RESULT_KEY = "filterResultKey"
        private const val FILTER_BUNDLE_KEY = "filterBundleKey"

        @JvmStatic
        fun newInstance() = NewsFragment()
    }
}