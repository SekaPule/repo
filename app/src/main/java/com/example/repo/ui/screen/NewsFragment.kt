package com.example.repo.ui.screen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.repo.R
import com.example.repo.data.DataProvider
import com.example.repo.databinding.FragmentNewsBinding
import com.example.repo.model.News
import com.example.repo.recycler.adapter.NewsAdapter
import com.example.repo.recycler.utils.NewsMarginItemDecoration
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private lateinit var dataProvider: DataProvider
    private val newsAdapter by lazy { NewsAdapter() }
    private var newsList: MutableList<News>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataProvider = DataProvider(requireContext())
        binding = FragmentNewsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        val handler = Handler(Looper.getMainLooper())

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
            executor.submit {
                Thread.sleep(5000)
                newsList = dataProvider.getNewsFromAssets() as MutableList<News>

                handler.post {
                    binding.progressBar.visibility = View.GONE
                    newsAdapter.submitList(newsList)
                }
            }
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
            }
        }
    }

    override fun onResume() {
        super.onResume()

        newsAdapter.submitList(newsList)
    }

    companion object {
        private const val FILTER_RESULT_KEY = "filterResultKey"
        private const val FILTER_BUNDLE_KEY = "filterBundleKey"

        @JvmStatic
        fun newInstance() = NewsFragment()
    }
}